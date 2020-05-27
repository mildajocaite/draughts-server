package lt.draughts.mapper;

import lt.draughts.entities.*;
import lt.draughts.entities.dto.UserDTO;
import lt.draughts.entities.request.RegistrationForm;
import lt.draughts.repository.BoardRepository;
import lt.draughts.repository.RoleRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    public User convertRegistrationUserToUser(RegistrationForm registrationForm) {
        if (registrationForm.getRole().equals("ROLE_STUDENT")) {
            Student student = new Student();
            student.setEmail(registrationForm.getEmail());
            student.setFirstname(registrationForm.getFirstname());
            student.setLastName(registrationForm.getLastname());
            student.setPassword(encoder.encode(registrationForm.getPassword()));
            student.setPhone(registrationForm.getPhone());
            student.setRole(getRoles(registrationForm.getRole()));
            if (registrationForm.getBoardCode() != null) {
                Board board = boardRepository.getByCode(registrationForm.getBoardCode());
                student.setBoard(board);
            }
            if (registrationForm.getCoach() != null) {
                Coach coach = userRepository.getCoachByEmail(registrationForm.getCoach());
                student.setCoach(coach);
            }
            return student;
        } else {
            Coach coach = new Coach();
            coach.setEmail(registrationForm.getEmail());
            coach.setFirstname(registrationForm.getFirstname());
            coach.setLastName(registrationForm.getLastname());
            coach.setPassword(encoder.encode(registrationForm.getPassword()));
            coach.setPhone(registrationForm.getPhone());
            coach.setRole(getRoles(registrationForm.getRole()));
            return coach;
        }
    }

    public UserDTO convertUserToUserDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setId(user.getId());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole().getName().toString());
        if (user instanceof Student && ((Student) user).getBoard() != null) {
            userDTO.setBoardCode(((Student) user).getBoard().getCode());
            userDTO.setBoardName(((Student) user).getBoard().getName());
        }
        if (user instanceof Student && ((Student) user).getCoach() != null) {
            UserDTO coach = this.convertUserToUserDTO(((Student) user).getCoach());
            userDTO.setCoach(coach);
        }
        return userDTO;
    }


    public User convertUserDTOToUser(UserDTO userDTO) {
        if (userDTO.getRole().equals("ROLE_STUDENT")) {
            Student student = new Student();
            student.setId(userDTO.getId());
            student.setEmail(userDTO.getEmail());
            student.setFirstname(userDTO.getFirstname());
            student.setLastName(userDTO.getLastname());
            student.setPhone(userDTO.getPhone());
            student.setRole(getRoles(userDTO.getRole()));
            if (userDTO.getBoardCode() != null) {
                Board board = boardRepository.getByCode(userDTO.getBoardCode());
                student.setBoard(board);
            }
            return student;
        } else {
            Coach coach = new Coach();
            coach.setId(userDTO.getId());
            coach.setEmail(userDTO.getEmail());
            coach.setFirstname(userDTO.getFirstname());
            coach.setLastName(userDTO.getLastname());
            coach.setPhone(userDTO.getPhone());
            coach.setRole(getRoles(userDTO.getRole()));
            return coach;
        }
    }

    private Role getRoles(String role) {
        return role.equals("ROLE_COACH")
                ? roleRepository.findByName(RoleName.ROLE_COACH.toString())
                : roleRepository.findByName(RoleName.ROLE_STUDENT.toString());
    }
}
