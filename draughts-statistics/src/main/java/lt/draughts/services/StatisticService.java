package lt.draughts.services;

import lt.draughts.entities.dto.ResolvedTasksPerDay;
import lt.draughts.entities.dto.StudentsStatistics;
import lt.draughts.entities.dto.TopTask;
import lt.draughts.entities.dto.UsersSortedByResolvedTasks;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<ResolvedTasksPerDay> getNumberOfResolvedTasksPerDay(String email, LocalDate start, LocalDate finish);

    List<UsersSortedByResolvedTasks> getUsersByResolvedTasks(Date start, Date end, String email);

    List<TopTask> getTop10MostPopularTasks();

    StudentsStatistics getStudentsStatistics(String email);
}
