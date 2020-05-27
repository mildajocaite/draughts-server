package lt.draughts.mapper;

import lt.draughts.entities.TaskForUser;
import lt.draughts.entities.dto.TaskDTO;
import lt.draughts.entities.dto.TaskForUserDTO;
import lt.draughts.repository.TaskRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskForUserMapper {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskMapper taskMapper;

    public TaskForUserDTO convertTaskRForUserToTaskForUserDTO(TaskForUser taskForUser) {
        TaskForUserDTO taskForUserDTO = new TaskForUserDTO();
        taskForUserDTO.setId(taskForUser.getId());
        taskForUserDTO.setResolvedDate(taskForUser.getResolvedDate());
        taskForUserDTO.setStartDate(taskForUser.getStart());
        TaskDTO taskDTO = taskMapper.convertTaskToTaskDTO(taskForUser.getTask());
        taskForUserDTO.setTaskDTO(taskDTO);
        taskForUserDTO.setTaskStatus(taskForUser.getTaskStatus());
        if (taskForUser.getUser() != null) {
            taskForUserDTO.setUserID(taskForUser.getUser().getId());
        }
        return taskForUserDTO;
    }
}
