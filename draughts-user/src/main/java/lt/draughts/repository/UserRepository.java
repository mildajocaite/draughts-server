package lt.draughts.repository;

import lt.draughts.entities.Coach;
import lt.draughts.entities.Student;
import lt.draughts.entities.User;

import java.util.List;

public interface UserRepository {
    User findByEmail(String email);

    List<User> getAll();

    Boolean existsByEmail(String email);

    void saveUser(User user);

    User findByID(Long id);

    void deleteUser(Long id);

    void updateUser(User user);

    List<Student> getStudents();

    List<Coach> getCoaches();

    Coach getCoachByEmail(String email);
}
