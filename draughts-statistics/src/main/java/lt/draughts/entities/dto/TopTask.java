package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lt.draughts.entities.TaskComplexity;
import lt.draughts.entities.TaskResult;
import lt.draughts.entities.TaskType;

@Getter
@Setter
public class TopTask {
    private int[][] position;
    private TaskType type;
    private TaskComplexity complexity;
    private TaskResult result;
    private long numberOfSolves;

    public TopTask(int[][] position, TaskResult taskResult, TaskType taskType, TaskComplexity taskComplexity, long numberOfSolves) {
        this.setPosition(position);
        this.setResult(taskResult);
        this.setComplexity(taskComplexity);
        this.setType(taskType);
        this.setNumberOfSolves(numberOfSolves);
    }
}
