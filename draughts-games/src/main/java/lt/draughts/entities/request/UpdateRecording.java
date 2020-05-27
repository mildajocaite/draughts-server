package lt.draughts.entities.request;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.RecordingStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UpdateRecording {
    @NotNull(message = "ID cannot be null.")
    private Long id;

    @NotNull(message = "Player1 cannot be null.")
    private String player1;

    @NotNull(message = "Player2 cannot be null.")
    private String player2;

    @NotNull(message = "Result cannot be null.")
    @NotNull(message = "Board code cannot be null.")
    private String board;

    private String result;

    @NotNull(message = "Status cannot be null")
    private RecordingStatus status;
}
