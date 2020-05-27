package lt.draughts.repository;

import lt.draughts.entities.Task;
import lt.draughts.entities.TaskForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TaskRepositoryImplementation implements TaskRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TaskForUserRepository taskForUserRepository;

    public void saveTask(Task task){
        entityManager.persist(task);
    }


    public Task getByID(long id){
            return entityManager.find(Task.class, id);
    }

    public List<Task> getAll() {
        return entityManager.createNamedQuery("Task.GetAll", Task.class)
                .getResultList();
    }

    public long getCount() {
        return entityManager.createNamedQuery("Task.Count", Long.class)
                .getSingleResult();
    }

    public void delete(long id){
        List<TaskForUser> tasksForUser = taskForUserRepository.getAll()
                .stream()
                .filter(item->item.getTask().getId()==id)
                .collect(Collectors.toList());
        tasksForUser.forEach(task->taskForUserRepository.deleteTaskForUser(task.getId()));
        Task task = getByID(id);
        entityManager.remove(task);
    }
}
