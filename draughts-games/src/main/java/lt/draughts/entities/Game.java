package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GAME")
@NamedQueries({
        @NamedQuery(name = "Game.GetAll", query = "select u from Game as u ")
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="player1_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name="player2_id")
    private User player2;

    @NotNull(message="Board code cannot be null.")
    @OneToOne
    private Board board;

    private String result;

    @NotNull(message = "Status cannot be null")
    private RecordingStatus status;

    @NotNull(message="StartRecording date cannot be null.")
    private Date startRecording;

    private Date endRecording;

    @ElementCollection
    @CollectionTable(name="game_moves")
    private List<String> moves = new ArrayList<>();

}
