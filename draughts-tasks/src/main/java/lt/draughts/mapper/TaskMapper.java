package lt.draughts.mapper;

import lt.draughts.entities.Task;
import lt.draughts.entities.dto.CreateTask;
import lt.draughts.entities.dto.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task convertCreateTaskToTask(CreateTask createTask) {
        Task task = new Task();
        task.setPosition(createTask.getPosition());
        task.setSolution(createTask.getSolution());
        task.setTaskComplexity(createTask.getTaskComplexity());
        task.setTaskType(createTask.getTaskType());
        task.setTaskResult(createTask.getTaskResult());
        return task;
    }

    public TaskDTO convertTaskToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setPosition(task.getPosition());
        taskDTO.setSolution(task.getSolution());
        taskDTO.setTaskComplexity(task.getTaskComplexity());
        taskDTO.setTaskResult(task.getTaskResult());
        taskDTO.setTaskType(task.getTaskType());
        return taskDTO;
    }
}
