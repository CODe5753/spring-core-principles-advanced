package hello.advanced.app.v4;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransferRepository extends JpaRepository<Account, Long> {
}
