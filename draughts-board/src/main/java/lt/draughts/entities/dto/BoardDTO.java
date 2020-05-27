package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class BoardDTO {
    @NotNull(message = "ID cannot be null.")
    private long id;

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Code cannot be null.")
    private String code;

    @NotNull(message = "CreatedDate cannot be null.")
    private Date createdDate;
}
