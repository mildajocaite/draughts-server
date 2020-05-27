package lt.draughts.repository;

import lt.draughts.entities.TaskComplexity;
import lt.draughts.entities.TaskType;
import lt.draughts.entities.dto.ResolvedTasksByType;
import lt.draughts.entities.dto.TopTask;
import lt.draughts.entities.dto.UserToDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class StatisticsRepositoryImplementation implements StatisticsRepository {

    @Autowired
    EntityManager entityManager;

    public long getNumberOfResolvedTaskByType(String email, TaskType taskType) {
        return (long) entityManager.createQuery(
                "SELECT COUNT(t.id) " +
                        "FROM TaskForUser as t " +
                        "JOIN t.user as u " +
                        "JOIN t.task as ta " +
                        "WHERE u.email=:email AND ta.taskType=:task_type AND t.taskStatus ='RESOLVED'"
        )
                .setParameter("email", email)
                .setParameter("task_type", taskType)
                .getSingleResult();
    }

    public long getNumberOfResolvedTaskByComplexity(String email, TaskComplexity taskComplexity) {
        return (long) entityManager.createQuery(
                "SELECT COUNT(t.id) " +
                        "FROM TaskForUser as t " +
                        "JOIN t.user as u " +
                        "JOIN t.task as ta " +
                        "WHERE u.email=:email AND ta.taskComplexity=:task_complexity AND t.taskStatus ='RESOLVED'"
        )
                .setParameter("email", email)
                .setParameter("task_complexity", taskComplexity)
                .getSingleResult();
    }

    public long getAmountOfResolvedTasks(String email) {
        return (long) entityManager.createQuery(
                "SELECT COUNT(t.id) " +
                        "FROM TaskForUser as t " +
                        "JOIN t.user as u " +
                        "WHERE u.email=:email AND t.taskStatus ='RESOLVED'"
        )
                .setParameter("email", email)
                .getSingleResult();
    }

    public double getAverageTime(String email) {
        long amountOfResolvedTasks = this.getAmountOfResolvedTasks(email);
        if (amountOfResolvedTasks == 0) {
            return 0;
        }

        long time = (long) entityManager.createQuery(
                "SELECT SUM( DATEDIFF(second, t.start,  t.resolvedDate) )" +
                        "FROM TaskForUser as t " +
                        "JOIN t.user as u " +
                        "WHERE u.email=:email AND t.taskStatus ='RESOLVED'"
        )
                .setParameter("email", email)
                .getSingleResult();

        return Math.round(time * 1.0 / amountOfResolvedTasks);
    }

    public double getAverageNumberOfTries(String email) {
        long amountOfResolvedTasks = this.getAmountOfResolvedTasks(email);
        if (amountOfResolvedTasks == 0) {
            return 0;
        }

        long sumOfTries = (long) entityManager.createQuery(
                "SELECT SUM(t.numberOfTries) " +
                        "FROM TaskForUser as t " +
                        "JOIN t.user as u " +
                        "WHERE u.email=:email AND t.taskStatus ='RESOLVED'"
        )
                .setParameter("email", email)
                .getSingleResult();

        return Math.round(sumOfTries * 1.0 / amountOfResolvedTasks);
    }

    public long getTotalNumberOfTasks() {
        return (long) entityManager.createQuery(
                "SELECT COUNT(t.id) " +
                        "FROM Task as t"
        )
                .getSingleResult();
    }

    public List<ResolvedTasksByType> getNumberOfResolvedTasks(String email) {
        return entityManager.createQuery(
                "SELECT new lt.draughts.entities.dto.ResolvedTasksByType( " +
                        "taskType.taskComplexity, " +
                        "COUNT(taskForUser.id)" +
                        ") " +
                        "FROM TaskForUser as taskForUser " +
                        "JOIN taskForUser.user as taskUser " +
                        "JOIN taskForUser.task as taskType " +
                        "WHERE taskUser.email=:email AND taskForUser.taskStatus='RESOLVED' " +
                        "GROUP BY taskUser.id, taskType.taskComplexity", ResolvedTasksByType.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<UserToDisplay> getStudentForStatistics() {
        return entityManager.createQuery(
                "SELECT new lt.draughts.entities.dto.UserToDisplay(user.email, user.lastName, user.firstname) " +
                        "FROM User as user " +
                        "JOIN user.role as role " +
                        "WHERE role.id=2", UserToDisplay.class)
                .getResultList();
    }

    public List<TopTask> getTopTasks() {
        return entityManager.createQuery(
                "SELECT new lt.draughts.entities.dto.TopTask(" +
                        "task.position, " +
                        "task.taskResult, " +
                        "task.taskType, " +
                        "task.taskComplexity, " +
                        "COUNT(taskForUser.id) " +
                        ") " +
                        "FROM TaskForUser as taskForUser " +
                        "JOIN taskForUser.task as task " +
                        "WHERE taskForUser.taskStatus='RESOLVED' " +
                        "GROUP BY task.position, task.taskResult, task.taskType, task.taskComplexity ", TopTask.class)
                .getResultList();
    }
}