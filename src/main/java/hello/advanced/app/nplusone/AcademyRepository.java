package hello.advanced.app.nplusone;

import hello.advanced.app.nplusone.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
}
