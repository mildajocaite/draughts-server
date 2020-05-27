package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.TaskComplexity;
import lt.draughts.entities.TaskResult;
import lt.draughts.entities.TaskType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskDTO {
    @NotNull(message = "ID cannot be null.")
    private long id;

    @NotNull(message = "Possition cannot be null.")
    private int[][] position;

    @NotNull(message = "Solution cannot be null.")
    private List<String> solution = new ArrayList<>();

    @NotNull(message = "Task Complexity cannot be null.")
    private TaskComplexity taskComplexity;

    @NotNull(message = "Task Type cannot be null.")
    private TaskType taskType;

    @NotNull(message = "Task Result cannot be null.")
    private TaskResult taskResult;
}
