package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryV1 {

    private final HelloTraceV1 trace;

    public void transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("TransferRepository.transfer()");
            // BL
            sleep(1000);
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
