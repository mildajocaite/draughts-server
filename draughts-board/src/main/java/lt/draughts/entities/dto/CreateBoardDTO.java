package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBoardDTO {
    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Code cannot be null.")
    private String code;
}
