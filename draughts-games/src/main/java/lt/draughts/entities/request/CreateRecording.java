package lt.draughts.entities.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateRecording {
    @NotNull(message = "Player cannot be null")
    private String player1;

    @NotNull(message = "Player cannot be null")
    private String player2;

    private String boardCode;
}
