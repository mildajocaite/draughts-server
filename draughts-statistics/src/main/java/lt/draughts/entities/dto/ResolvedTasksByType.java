package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.TaskComplexity;

@Getter
@Setter
public class ResolvedTasksByType {
    private TaskComplexity taskComplexity;
    private long numberOfTasks;

    public ResolvedTasksByType(TaskComplexity taskComplexity, long numberOfTasks) {
        this.setTaskComplexity(taskComplexity);
        this.setNumberOfTasks(numberOfTasks);
    }
}

