package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EditBoardDTO {
    @NotNull(message = "ID cannot be null.")
    private long id;

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Code cannot be null.")
    private String code;

    @NotNull(message = "UserID cannot be null.")
    private long userID;
}
