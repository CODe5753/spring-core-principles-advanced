package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.FieldLogTrace;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceV3 {

    private final TransferRepositoryV3 transferRepository;
    private final LogTrace trace;

    public void transferMoney(String srcAccountNumber, String destAccountNumber, int amount) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("TransferService.transferMoney()");
            transferRepository.transfer(srcAccountNumber, destAccountNumber, amount);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
