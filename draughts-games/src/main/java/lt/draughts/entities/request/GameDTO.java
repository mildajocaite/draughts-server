package lt.draughts.entities.request;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.RecordingStatus;
import lt.draughts.entities.dto.BoardDTO;
import lt.draughts.entities.dto.UserDTO;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GameDTO {
    @NotNull(message = "ID cannot be null.")
    private Long id;

    @NotNull(message = "Player1 cannot be null.")
    private UserDTO player1;

    @NotNull(message = "Player2 cannot be null.")
    private UserDTO player2;

    @NotNull(message = "Result cannot be null.")
    @NotNull(message = "Board code cannot be null.")
    private BoardDTO board;

    private String result;

    @NotNull(message = "Status cannot be null")
    private RecordingStatus status;

    @NotNull(message = "StartRecording date cannot be null.")
    private Date startRecording;

    private Date endRecording;

    @NotNull(message = "Result cannot be null.")
    private List<String> moves;
}