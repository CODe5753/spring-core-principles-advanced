package hello.advanced.app.v4;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v4")
@RequiredArgsConstructor
public class TransferControllerV4 {

    private final TransferServiceV4 transferService;
    private final LogTrace trace;

    @ApiOperation(value = "test hello")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/request")
    public String transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                transferService.transferMoney(srcAccountNumber, destAccountNumber, amount);
                return "ok";
            }
        };
        return template.execute("TransferController.transfer()");
    }
}
