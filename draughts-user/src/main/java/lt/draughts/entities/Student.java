package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Student extends User {

    @OneToOne
    private Board board;

    @ManyToOne
    private Coach coach;
}
