package hello.advanced.app.nplusone;

import hello.advanced.app.nplusone.entity.Academy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyService {

    private final AcademyRepository academyRepository;

    @Transactional(readOnly = true)
    public List<String> findAllSubjectNames() {
        return extractSubjectNames(academyRepository.findAll());
    }

    private List<String> extractSubjectNames(List<Academy> academies) {
        return academies.stream()
                .map(a -> a.getSubjects().get(0).getName())
                .collect(Collectors.toList());
    }
}
