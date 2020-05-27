package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticTaskType {
    private String type;
    private long numberOfResolvedTasks;
}
