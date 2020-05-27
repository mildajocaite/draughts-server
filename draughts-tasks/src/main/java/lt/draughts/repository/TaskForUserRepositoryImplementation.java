package lt.draughts.repository;

import lt.draughts.entities.TaskForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskForUserRepositoryImplementation implements TaskForUserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public void updateTaskForUser(TaskForUser taskForUser) {
        entityManager.merge(taskForUser);
    }

    @Override
    public void createTaskForUser(TaskForUser taskForUser) {
        entityManager.persist(taskForUser);
    }

    @Override
    public void deleteTaskForUser(long id) {
        TaskForUser taskForUser = entityManager.find(TaskForUser.class, id);
        entityManager.remove(taskForUser);
    }

    @Override
    public List<TaskForUser> getAll() {
        return entityManager
                .createNamedQuery("TaskForUser.GetAll", TaskForUser.class)
                .getResultList();
    }

    @Override
    public TaskForUser getByID(long id) {
        return entityManager.find(TaskForUser.class, id);
    }

    @Override
    public TaskForUser getByTaskIdAndUserEmail(long taskID, String email) {
        List<TaskForUser> taskForUserList = entityManager
                .createNamedQuery("TaskForUser.GetAllByUserAndTask", TaskForUser.class)
                .setParameter("taskID", taskID)
                .setParameter("email", email)
                .getResultList();

        if (taskForUserList.size() != 0) {
            return taskForUserList.get(0);
        }
        return null;
    }


    @Override
    public List<TaskForUser> getTasksForUser(String email) {
        List<TaskForUser> tasksForUser = entityManager
                .createNamedQuery("TaskForUser.GetUsersTasks", TaskForUser.class)
                .setParameter("email", email)
                .getResultList();
        return tasksForUser;
    }

    @Override
    public List<TaskForUser> getUsersResolvedTasks(String email) {
        List<TaskForUser> taskForUserList = entityManager
                .createNamedQuery("TaskForUser.GetUsersResolvedTasks", TaskForUser.class)
                .setParameter("email", email)
                .getResultList();
        return taskForUserList;
    }
}