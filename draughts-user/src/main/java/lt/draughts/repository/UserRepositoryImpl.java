package lt.draughts.repository;

import lt.draughts.entities.Coach;
import lt.draughts.entities.Student;
import lt.draughts.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByEmail(String email) {
        return entityManager
                .createNamedQuery("User.FindUserByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<User> getAll() {
        return entityManager
                .createNamedQuery("User.GetAll", User.class)
                .getResultList();
    }


    public Boolean existsByEmail(String email) {
        return entityManager.createNamedQuery("User.ExistByEmail", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public User findByID(Long id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public List<Student> getStudents() {
        return entityManager
                .createNamedQuery("User.GetStudents", User.class)
                .getResultList()
                .stream()
                .map(item -> (Student) item)
                .collect(Collectors.toList());
    }

    public List<Coach> getCoaches() {
        return entityManager
                .createNamedQuery("User.GetCoach", User.class)
                .getResultList()
                .stream()
                .map(item -> (Coach) item)
                .collect(Collectors.toList());
    }

    public Coach getCoachByEmail(String email) {
        return (Coach) entityManager.createNamedQuery("Coach.FindCoachByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}

