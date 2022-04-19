package hello.advanced.app.v0;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferControllerV0 {

    private final TransferServiceV0 transferService;

    @ApiOperation(value = "test hello")
    @GetMapping("/v0/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/v0/request")
    public String transfer(String srcAccountNumber, String destAccountNumber, int amount) {
        transferService.transferMoney(srcAccountNumber, destAccountNumber, amount);
        return "ok";
    }
}
