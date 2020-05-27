package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.TaskStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class TaskForUserDTO {

    @NotNull(message = "ID cannot be null.")
    private Long id;

    @NotNull(message = "Task cannot be null.")
    private TaskDTO taskDTO;

    @NotNull(message = "Task Resolution cannot be null.")
    private TaskStatus taskStatus;

    private Date resolvedDate;

    private Date startDate;

    @NotNull(message = "UserID cannot be null.")
    private long userID;
}
