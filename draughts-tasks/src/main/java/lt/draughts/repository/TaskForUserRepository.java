package lt.draughts.repository;

import lt.draughts.entities.TaskForUser;

import java.util.List;

public interface TaskForUserRepository {

    void updateTaskForUser(TaskForUser taskForUser);

    void createTaskForUser(TaskForUser taskForUser);

    void deleteTaskForUser(long id);

    List<TaskForUser> getAll();

    TaskForUser getByID(long id);

    TaskForUser getByTaskIdAndUserEmail(long taskID, String email);

    List<TaskForUser> getTasksForUser(String email);

    List<TaskForUser> getUsersResolvedTasks(String email);
}
