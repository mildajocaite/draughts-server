package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResolvedTasksPerDay {
    private String resolvedDate;
    private int numberOfResolvedTasks;
}
