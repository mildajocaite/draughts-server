package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Role.FindByName", query = "select r from Role as r " +
                "WHERE r.name=:name"),
        @NamedQuery(name = "Role.GetAll", query = "select r from Role as r ")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

}