package lt.draughts.services;

import lt.draughts.entities.dto.CreateTask;
import lt.draughts.entities.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(CreateTask createTask);

    TaskDTO getByID(long id);

    List<TaskDTO> getAll();

    void deleteTask(long id);
}
