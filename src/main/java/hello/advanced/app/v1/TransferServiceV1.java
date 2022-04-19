package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceV1 {

    private final TransferRepositoryV1 transferRepository;
    private final HelloTraceV1 trace;

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
