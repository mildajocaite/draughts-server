package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentsStatistics {
    private long numberOfResolvedEasyTasks;
    private long numberOfResolvedMediumTasks;
    private long numberOfResolvedHardTasks;
    private long total;
    private List<StatisticTaskType> tasksTypeStatistics;
    private double numberOfTries;
    private double numberOfResolutionTimes;
}