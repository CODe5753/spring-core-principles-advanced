package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.FieldLogTrace;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryV3 {

    private final LogTrace trace;

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
