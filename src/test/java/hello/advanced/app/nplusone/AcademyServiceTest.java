package hello.advanced.app.nplusone;

import hello.advanced.app.nplusone.entity.Academy;
import hello.advanced.app.nplusone.entity.Subject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class AcademyServiceTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyService academyService;

    @BeforeEach
    void setup() {
        List<Academy> academies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Academy academy = Academy.builder()
                    .name("강남스쿨" + i)
                    .subjects(new ArrayList<>())
                    .build();
            academy.addSubject(Subject.builder().name("자바웹개발" + i).build());
            academies.add(academy);
        }
        academyRepository.saveAll(academies);
    }

    @Test
    void Academy여러개를_조회시_Subject가_N1_쿼리발생() throws Exception {
        // given
        List<String> subjectNames = academyService.findAllSubjectNames();

        // then
        assertThat(subjectNames.size()).isEqualTo(10);
    }
}