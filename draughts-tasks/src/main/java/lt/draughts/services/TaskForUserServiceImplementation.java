package lt.draughts.services;

import lt.draughts.entities.*;
import lt.draughts.entities.dto.TaskForUserDTO;
import lt.draughts.exceptions.BoardDoesNotExist;
import lt.draughts.mapper.TaskForUserMapper;
import lt.draughts.repository.TaskForUserRepository;
import lt.draughts.repository.TaskRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskForUserServiceImplementation implements TaskForUserService {

    @Autowired
    TaskForUserRepository taskForUserRepository;

    @Autowired
    TaskForUserMapper taskForUserMapper;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordingService recordingService;

    @Override
    public List<TaskForUserDTO> getAll(String email) {
        List<Task> tasks = taskRepository.getAll();
        List<TaskForUserDTO> taskForUserDTOS = new ArrayList<>();
        tasks.forEach(task -> {
            TaskForUser taskForUser = taskForUserRepository.getByTaskIdAndUserEmail(task.getId(), email);
            if (taskForUser == null || taskForUser.getTaskStatus() == TaskStatus.CORRECT_POSITION) {
                taskForUser = new TaskForUser();
                taskForUser.setTaskStatus(TaskStatus.NOT_RESOLVED);
            }
            taskForUser.setTask(task);
            taskForUserDTOS.add(taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser));
        });
        return taskForUserDTOS;
    }


    @Override
    public TaskForUserDTO getByID(long id) {
        TaskForUser taskForUser = taskForUserRepository.getByID(id);
        if (taskForUser.getTaskStatus() != TaskStatus.RESOLVED) {
            taskForUser.setTaskStatus(TaskStatus.NOT_RESOLVED);
        }
        taskForUser.setNumberOfTries(taskForUser.getNumberOfTries() + 1);
        return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
    }

    @Override
    public TaskForUserDTO createTaskForUser(long taskID, String email) {
        User user = userRepository.findByEmail(email);
        if (((Student) user).getBoard() == null) {
            throw new BoardDoesNotExist("Nėra pridėta interaktyvi lenta.");
        }
        TaskForUser taskForUserExisting = taskForUserRepository.getByTaskIdAndUserEmail(taskID, email);
        if (taskForUserExisting == null) {
            TaskForUser taskForUser = new TaskForUser();
            taskForUser.setUser(user);
            Task task = taskRepository.getByID(taskID);
            taskForUser.setTask(task);
            taskForUser.setTaskStatus(TaskStatus.NOT_RESOLVED);
            taskForUser.setStart(new Date());
            taskForUser.setNumberOfTries(0);
            taskForUserRepository.createTaskForUser(taskForUser);
            TaskForUser createdTaskForUser = taskForUserRepository.getByTaskIdAndUserEmail(taskForUser.getTask().getId(), taskForUser.getUser().getEmail());
            return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(createdTaskForUser);
        }
        if (taskForUserExisting.getTaskStatus() != TaskStatus.RESOLVED) {
            taskForUserExisting.setStart(new Date());
            taskForUserExisting.setTaskStatus(TaskStatus.NOT_RESOLVED);
            taskForUserExisting.setNumberOfTries(taskForUserExisting.getNumberOfTries() + 1);
            taskForUserRepository.updateTaskForUser(taskForUserExisting);
        }
        return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUserExisting);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findByID(id);
        if (user instanceof Coach) {
            userRepository
                    .getStudents()
                    .stream()
                    .filter(student -> student.getCoach()!=null && student.getCoach().getEmail().equals(user.getEmail()))
                    .forEach(student -> student.setCoach(null));
        }
        List<TaskForUser> tasksForUser = taskForUserRepository.getTasksForUser(user.getEmail());
        tasksForUser.forEach(task -> {
                    task.setUser(null);
                    taskForUserRepository.deleteTaskForUser(task.getId());
                }
        );
        if (user instanceof Student) {
            ((Student) user).setBoard(null);
        }
        userRepository.deleteUser(user.getId());
    }

    @Override
    public TaskForUserDTO checkIfStartPositionCorrect(long id) {
        TaskForUser taskForUser = taskForUserRepository.getByID(id);
        if (taskForUser == null) {
            return null;
        }
        Date startDate = recordingService.getStartPositionDate(
                ((Student) taskForUser.getUser()).getBoard().getCode(),
                taskForUser.getStart(),
                taskForUser.getTask().getPosition()
        );
        if (startDate != null) {
            taskForUser.setTaskStatus(TaskStatus.CORRECT_POSITION);
            taskForUser.setStart(startDate);
            taskForUserRepository.updateTaskForUser(taskForUser);
            return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
        }
        return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
    }

    @Override
    public TaskForUserDTO checkIfSolutionRight(long id) {
        TaskForUser taskForUser = taskForUserRepository.getByID(id);
        if (taskForUser == null) {
            return null;
        }
        List<String> movesFromThingSpeak = recordingService.getMoves(
                ((Student) taskForUser.getUser()).getBoard().getCode(),
                taskForUser.getStart()
        );
        boolean isEqual = true;
        if (taskForUser.getTask().getSolution().size() > movesFromThingSpeak.size()) {
            return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
        }
        for (int i = 0; i < taskForUser.getTask().getSolution().size(); i++) {
            if (!taskForUser.getTask().getSolution().get(i).equals(movesFromThingSpeak.get(i))) {
                isEqual = false;
            }
        }
        if (isEqual && taskForUser.getTaskStatus().equals(TaskStatus.CORRECT_POSITION)) {
            taskForUser.setResolvedDate(new Date());
            taskForUser.setTaskStatus(TaskStatus.RESOLVED);
            taskForUserRepository.updateTaskForUser(taskForUser);
            return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
        }
        return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
    }

    @Override
    public TaskForUserDTO getByTaskIDEmail(String email, long taskID) {
        TaskForUser taskForUser = taskForUserRepository.getByTaskIdAndUserEmail(taskID, email);
        return taskForUserMapper.convertTaskRForUserToTaskForUserDTO(taskForUser);
    }
}
