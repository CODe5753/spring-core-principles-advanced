package hello.advanced.app.v2;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceV2 {

    private final TransferRepositoryV2 transferRepository;
    private final HelloTraceV2 trace;

    public void transferMoney(TraceId traceId, String srcAccountNumber, String destAccountNumber, int amount) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.beginSync(traceId, "TransferService.transferMoney()");
            transferRepository.transfer(traceStatus.getTraceId(), srcAccountNumber, destAccountNumber, amount);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
