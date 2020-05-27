package lt.draughts.services;

import lt.draughts.entities.dto.TaskForUserDTO;

import java.util.List;

public interface TaskForUserService {
    List<TaskForUserDTO> getAll(String email);

    TaskForUserDTO getByID(long id);

    TaskForUserDTO checkIfStartPositionCorrect(long id);

    TaskForUserDTO checkIfSolutionRight(long id);

    TaskForUserDTO getByTaskIDEmail(String email, long taskID);

    TaskForUserDTO createTaskForUser(long id, String email);

    void deleteUser(Long id);
}
