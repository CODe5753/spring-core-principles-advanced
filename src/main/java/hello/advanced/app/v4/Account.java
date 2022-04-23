package hello.advanced.app.v4;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    Long userId;

    int money;
}
