package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.FieldLogTrace;
import hello.advanced.app.trace.logtrace.LogTrace;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3")
@RequiredArgsConstructor
public class TransferControllerV3 {

    private final TransferServiceV3 transferService;
    private final LogTrace trace;

    @ApiOperation(value = "test hello")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/request")
    public String transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("TransferController.transfer()");
            transferService.transferMoney(srcAccountNumber, destAccountNumber, amount);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
        return "ok";
    }
}
