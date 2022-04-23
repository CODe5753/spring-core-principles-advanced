package hello.advanced.app.v4;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceV4 {

    private final TransferRepositoryV4Support transferRepository;
    private final LogTrace trace;

    public void transferMoney(long srcAccountNumber, long destAccountNumber, int amount) {
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                transferRepository.transfer(srcAccountNumber, destAccountNumber, amount);
                return null;
            }
        };
        template.execute("TransferService.transferMoney()");
    }
}
