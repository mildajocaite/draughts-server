package lt.draughts.services;

import lt.draughts.entities.Coach;
import lt.draughts.entities.Student;
import lt.draughts.entities.User;
import lt.draughts.entities.dto.UserDTO;
import lt.draughts.entities.request.RegistrationForm;
import lt.draughts.exceptions.BoardIsAssigned;
import lt.draughts.exceptions.EmailAlreadyExists;
import lt.draughts.mapper.UserMapper;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User createUser(RegistrationForm registrationForm) {
        User user = userMapper.convertRegistrationUserToUser(registrationForm);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExists("El. paštas jau egzistuoja.");
        }
        if (userRepository.getAll()
                .stream()
                .anyMatch(record -> record instanceof Student
                        && ((Student) record).getBoard().getCode().equals(registrationForm.getBoardCode())
                )) {
            throw new BoardIsAssigned("Lenta yra priskirta kitam naudotojui.");
        }
        userRepository.saveUser(user);
        return user;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.getAll()
                .stream()
                .map(p -> userMapper.convertUserToUserDTO(p))
                .collect(Collectors.toList());
        users.sort(Comparator.comparing(UserDTO::getEmail).reversed());
        return users;
    }

    public UserDTO getUserById(long id) {
        return userMapper.convertUserToUserDTO(userRepository.findByID(id));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userMapper.convertUserDTOToUser(userDTO);
        User oldUser = userRepository.findByID(userDTO.getId());
        user.setPassword(oldUser.getPassword());
        if (!userDTO.getEmail().equals(oldUser.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExists("El. paštas jau egzistuoja.");
        }

        boolean boardAlreadyExists = userDTO.getBoardCode() != null && userRepository.getAll()
                .stream()
                .anyMatch(record -> record instanceof Student
                        && ((Student) record).getBoard().getCode().equals(userDTO.getBoardCode())
                );
        if (boardAlreadyExists && ((Student) oldUser).getBoard() == null) {
            throw new BoardIsAssigned("Lenta yra priskirta kitam naudotojui.");
        }
        if (user instanceof Student
                && oldUser instanceof Student
                && ((Student) user).getBoard() != null
                && boardAlreadyExists
                && ((Student) user).getBoard().getCode().equals(((Student) oldUser).getBoard().getCode())
        ) {
            throw new BoardIsAssigned("Lenta yra priskirta kitam naudotojui.");
        }
        userRepository.updateUser(user);
        return userMapper.convertUserToUserDTO(userRepository.findByID(user.getId()));
    }

    public List<UserDTO> getCoaches() {
        List<Coach> coaches = userRepository.getCoaches();
        return coaches.stream().map(item -> userMapper.convertUserToUserDTO(item)).collect(Collectors.toList());
    }

    public List<UserDTO> getStudents() {
        List<Student> students = userRepository.getStudents();
        return students.stream().map(item -> userMapper.convertUserToUserDTO(item)).collect(Collectors.toList());
    }
}
