package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceV0 {

    private final TransferRepositoryV0 transferRepository;

    public void transferMoney(String srcAccountNumber, String destAccountNumber, int amount){
        transferRepository.transfer(srcAccountNumber, destAccountNumber, amount);
    }
}
