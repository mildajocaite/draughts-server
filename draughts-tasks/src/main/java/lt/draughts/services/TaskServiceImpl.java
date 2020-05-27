package lt.draughts.services;

import lt.draughts.entities.Task;
import lt.draughts.entities.dto.CreateTask;
import lt.draughts.entities.dto.TaskDTO;
import lt.draughts.mapper.TaskMapper;
import lt.draughts.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskRepository taskRepository;

    public TaskDTO createTask(CreateTask createTask) {
        Task task = taskMapper.convertCreateTaskToTask(createTask);
        taskRepository.saveTask(task);
        return taskMapper.convertTaskToTaskDTO(task);
    }

    public TaskDTO getByID(long id) {
        Task task = taskRepository.getByID(id);
        return taskMapper.convertTaskToTaskDTO(task);
    }

    public List<TaskDTO> getAll() {
        List<TaskDTO> tasks = taskRepository
                .getAll()
                .stream()
                .map(task -> taskMapper.convertTaskToTaskDTO(task))
                .collect(Collectors.toList());
        tasks.sort(Comparator.comparing(TaskDTO::getId).reversed());
        return tasks;
    }

    public void deleteTask(long id) {
        taskRepository.delete(id);
    }
}
