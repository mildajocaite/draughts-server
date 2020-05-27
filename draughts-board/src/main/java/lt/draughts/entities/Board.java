package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "BOARD")
@NamedQueries({
        @NamedQuery(name = "Board.GetAll", query = "SELECT t " +
                "FROM Board as t "),
        @NamedQuery(name = "Board.GetByCode", query = "SELECT t " +
                "FROM Board as t " +
                "WHERE t.code=:code")
})
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Code cannot be null.")
    private String code;

    @NotNull(message = "Created date cannot be null.")
    @Column(name = "createdDate")
    private Date createdDate;
}