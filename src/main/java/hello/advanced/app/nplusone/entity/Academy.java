package hello.advanced.app.nplusone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Academy {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="academy_id")
    private List<Subject> subjects = new ArrayList<>();

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.updateAcademy(this);
    }
}
