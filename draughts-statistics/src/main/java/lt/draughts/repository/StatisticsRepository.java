package lt.draughts.repository;

import lt.draughts.entities.TaskComplexity;
import lt.draughts.entities.TaskType;
import lt.draughts.entities.dto.ResolvedTasksByType;
import lt.draughts.entities.dto.TopTask;
import lt.draughts.entities.dto.UserToDisplay;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatisticsRepository {
    long getNumberOfResolvedTaskByType(String email, TaskType taskType);

    long getNumberOfResolvedTaskByComplexity(String email, TaskComplexity taskComplexity);

    double getAverageTime(String email);

    long getAmountOfResolvedTasks(String email);

    double getAverageNumberOfTries(String email);

    long getTotalNumberOfTasks();

    List<ResolvedTasksByType> getNumberOfResolvedTasks(String email);

    List<UserToDisplay> getStudentForStatistics();

    List<TopTask> getTopTasks();
}
