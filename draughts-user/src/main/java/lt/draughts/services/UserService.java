package lt.draughts.services;

import lt.draughts.entities.User;
import lt.draughts.entities.dto.UserDTO;
import lt.draughts.entities.request.RegistrationForm;

import java.util.List;

public interface UserService {
    User createUser(RegistrationForm registrationForm);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(long id);

    UserDTO updateUser(UserDTO user);

    List<UserDTO> getCoaches();

    List<UserDTO> getStudents();
}
