package hello.advanced.app.v4;

import hello.advanced.QuerydslJpaQueryConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransferControllerTest {
    @Autowired
    TransferRepository transferRepository;

    @Test
    void createTest() {
        Long userId = 1L;
        int amount = 0;
        Account userA = Account.builder()
                .userId(userId)
                .money(amount)
                .build();
        transferRepository.save(userA);
        Account result = transferRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("찾기 실패"));
        assertThat(result.money).isEqualTo(amount);
        assertThat(result.userId).isEqualTo(userId);
    }
}
