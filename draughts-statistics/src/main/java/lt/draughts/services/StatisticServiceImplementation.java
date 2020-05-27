package lt.draughts.services;

import lt.draughts.entities.*;
import lt.draughts.entities.dto.*;
import lt.draughts.mapper.TaskMapper;
import lt.draughts.mapper.UserMapper;
import lt.draughts.repository.StatisticsRepository;
import lt.draughts.repository.TaskForUserRepository;
import lt.draughts.repository.TaskRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StatisticServiceImplementation implements StatisticService {

    @Autowired
    TaskForUserRepository taskForUserRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    StatisticsRepository statisticsRepository;

    public StudentsStatistics getStudentsStatistics(String email) {
        StudentsStatistics studentsStatistics = new StudentsStatistics();
        List<StatisticTaskType> statisticsTasksType = new ArrayList<>();
        StatisticTaskType taskType1 = new StatisticTaskType();
        taskType1.setType("ŽIRKLĖS");
        taskType1.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.ZIRKLES));
        statisticsTasksType.add(taskType1);
        StatisticTaskType taskType2 = new StatisticTaskType();
        taskType2.setType("KOMBINACIJA");
        taskType2.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.KOMBINACIJA));
        statisticsTasksType.add(taskType2);
        StatisticTaskType taskType3 = new StatisticTaskType();
        taskType3.setType("ENDŠPILIS");
        taskType3.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.ENDSPILIS));
        statisticsTasksType.add(taskType3);
        StatisticTaskType taskType4 = new StatisticTaskType();
        taskType4.setType("OPOZICIJA");
        taskType4.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.OPOZICIJA));
        statisticsTasksType.add(taskType4);
        StatisticTaskType taskType5 = new StatisticTaskType();
        taskType5.setType("KILPA");
        taskType5.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.KILPA));
        statisticsTasksType.add(taskType5);
        StatisticTaskType taskType6 = new StatisticTaskType();
        taskType6.setType("UŽDARYMAS");
        taskType6.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.UŽDARYMAS));
        statisticsTasksType.add(taskType6);
        StatisticTaskType taskType7 = new StatisticTaskType();
        taskType7.setType("ATKIRTIMAS");
        taskType7.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.ATKIRTIMAS));
        statisticsTasksType.add(taskType7);
        StatisticTaskType taskType8 = new StatisticTaskType();
        taskType8.setType("KITA");
        taskType8.setNumberOfResolvedTasks(statisticsRepository.getNumberOfResolvedTaskByType(email, TaskType.KILPA));
        statisticsTasksType.add(taskType8);
        studentsStatistics.setTasksTypeStatistics(statisticsTasksType);
        studentsStatistics.setNumberOfResolvedEasyTasks(statisticsRepository.getNumberOfResolvedTaskByComplexity(email, TaskComplexity.EASY));
        studentsStatistics.setNumberOfResolvedMediumTasks(statisticsRepository.getNumberOfResolvedTaskByComplexity(email, TaskComplexity.MEDIUM));
        studentsStatistics.setNumberOfResolvedHardTasks(statisticsRepository.getNumberOfResolvedTaskByComplexity(email, TaskComplexity.HARD));
        studentsStatistics.setNumberOfResolutionTimes(statisticsRepository.getAverageTime(email));
        studentsStatistics.setNumberOfTries(statisticsRepository.getAverageNumberOfTries(email));
        studentsStatistics.setTotal(statisticsRepository.getTotalNumberOfTasks());
        return studentsStatistics;
    }

    @Override
    public List<ResolvedTasksPerDay> getNumberOfResolvedTasksPerDay(String email, LocalDate start, LocalDate finish) {
        List<TaskForUser> taskForUserList = taskForUserRepository.getUsersResolvedTasks(email);

        long numOfDaysBetween = ChronoUnit.DAYS.between(start, finish.plusDays(1));
        List<LocalDate> dates = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> start.plusDays(i))
                .collect(Collectors.toList());
        Map<String, List<TaskForUser>> taskForUserListGroup = taskForUserList.stream()
                .collect(Collectors.groupingBy(item ->
                        new SimpleDateFormat("yyyy-MM-dd")
                                .format(item.getResolvedDate())));
        List<ResolvedTasksPerDay> resolvedTasksPerDayList = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            ResolvedTasksPerDay resolvedTasksPerDay = new ResolvedTasksPerDay();
            String dateToString = dates.get(i).toString();
            resolvedTasksPerDay.setResolvedDate(dateToString);
            if (taskForUserListGroup.containsKey(dateToString)) {
                resolvedTasksPerDay.setNumberOfResolvedTasks(taskForUserListGroup.get(dateToString).size());
            } else {
                resolvedTasksPerDay.setNumberOfResolvedTasks(0);
            }
            resolvedTasksPerDayList.add(resolvedTasksPerDay);
        }
        return resolvedTasksPerDayList;
    }

    @Override
    public List<UsersSortedByResolvedTasks> getUsersByResolvedTasks(Date start, Date end, String email) {
        List<UserToDisplay> studentsEmail = statisticsRepository.getStudentForStatistics();
        List<UsersSortedByResolvedTasks> topStudents = new ArrayList<>();
        for (UserToDisplay student : studentsEmail
        ) {
            List<ResolvedTasksByType> resolvedTasksByTypes = this.statisticsRepository.getNumberOfResolvedTasks(student.getEmail());
            UsersSortedByResolvedTasks usersSortedByResolvedTasks = new UsersSortedByResolvedTasks();
            usersSortedByResolvedTasks.setLastName(student.getLastName());
            usersSortedByResolvedTasks.setFirstName(student.getFirstName());
            usersSortedByResolvedTasks.setEmail(student.getEmail());
            List<ResolvedTasksByType> easyTasks = resolvedTasksByTypes
                    .stream()
                    .filter(item -> item.getTaskComplexity() == TaskComplexity.EASY)
                    .collect(Collectors.toList());
            usersSortedByResolvedTasks.setNumberOfEasyTasks(easyTasks.size() != 0 ? easyTasks.get(0).getNumberOfTasks() : 0);
            List<ResolvedTasksByType> mediumTasks = resolvedTasksByTypes
                    .stream()
                    .filter(item -> item.getTaskComplexity() == TaskComplexity.MEDIUM)
                    .collect(Collectors.toList());
            usersSortedByResolvedTasks.setNumberOfMediumTasks(mediumTasks.size() != 0 ? mediumTasks.get(0).getNumberOfTasks() : 0);
            List<ResolvedTasksByType> hardTasks = resolvedTasksByTypes
                    .stream()
                    .filter(item -> item.getTaskComplexity() == TaskComplexity.HARD)
                    .collect(Collectors.toList());
            usersSortedByResolvedTasks.setNumberOfHardTasks(hardTasks.size() != 0 ? hardTasks.get(0).getNumberOfTasks() : 0);
            topStudents.add(usersSortedByResolvedTasks);
        }
        for (int i = 0; i < topStudents.size(); i++) {
            topStudents.get(i).setPlace(i + 1);
        }
        User user = userRepository.findByEmail(email);
        if (user.getRole().equals(RoleName.ROLE_STUDENT)) {
            return topStudents.stream().limit(10).collect(Collectors.toList());
        }
        return topStudents;
    }

    @Override
    public List<TopTask> getTop10MostPopularTasks() {
        List<TopTask> topTasks = statisticsRepository.getTopTasks();
        topTasks.sort(Comparator.comparing(TopTask::getNumberOfSolves).reversed());
        return topTasks;
    }
}
