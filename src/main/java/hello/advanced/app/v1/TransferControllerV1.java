package hello.advanced.app.v1;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferControllerV1 {

    private final TransferServiceV1 transferService;

    @ApiOperation(value = "test hello")
    @GetMapping("/v1/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/v1/request")
    public String transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        transferService.transferMoney(srcAccountNumber, destAccountNumber, amount);
        return "ok";
    }
}
