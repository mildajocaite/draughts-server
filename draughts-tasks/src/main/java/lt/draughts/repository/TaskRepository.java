package lt.draughts.repository;

import lt.draughts.entities.Task;

import java.util.List;

public interface TaskRepository {
    void saveTask(Task task);
    List<Task> getAll();
    Task getByID(long id);
    long getCount();
    void delete(long id);
}
