package hello.advanced.app.v2;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferControllerV2 {

    private final TransferServiceV2 transferService;
    private final HelloTraceV2 trace;

    @ApiOperation(value = "test hello")
    @GetMapping("/v2/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/v2/request")
    public String transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("TransferController.transfer()");
            transferService.transferMoney(traceStatus.getTraceId(), srcAccountNumber, destAccountNumber, amount);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
        return "ok";
    }
}
