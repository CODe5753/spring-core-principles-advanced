package hello.advanced.app.v4;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryV4Support {

    private final LogTrace trace;
    private final JPAQueryFactory jpaQueryFactory;

    QAccount qAccount = QAccount.account;

    public void transfer(long srcAccountNumber, long destAccountNumber, int amount) {
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            @Transactional
            protected Void call() {
                int srcCurrentAmount = jpaQueryFactory.select(qAccount.money)
                        .from(qAccount)
                        .where(qAccount.userId.eq(srcAccountNumber))
                        .fetchFirst();
                if (srcCurrentAmount - amount < 0) {    // 잔고 부족
                    throw new IllegalArgumentException();
                }
                jpaQueryFactory.update(qAccount)
                        .where(qAccount.userId.eq(srcAccountNumber))
                        .set(qAccount.money, srcCurrentAmount - amount)
                        .execute();
                int destCurrentAmount = jpaQueryFactory.select(qAccount.money)
                        .from(qAccount)
                        .where(qAccount.userId.eq(srcAccountNumber))
                        .fetchFirst();
                jpaQueryFactory.update(qAccount)
                        .where(qAccount.userId.eq(destAccountNumber))
                        .set(qAccount.money, destCurrentAmount + amount)
                        .execute();
                sleep(500);
                return null;
            }
        };
        template.execute("TransferRepository.transfer()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
