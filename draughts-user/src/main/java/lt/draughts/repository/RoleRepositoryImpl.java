package lt.draughts.repository;

import lt.draughts.entities.Role;
import lt.draughts.exceptions.RoleDoesNotExit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Role findByName(String name) {
        List<Role> roles = entityManager
                .createNamedQuery("Role.GetAll", Role.class)
                .getResultList();
        return roles.stream()
                .filter(e -> e.getName().toString().equals(name))
                .findAny()
                .orElseThrow(() -> new RoleDoesNotExit("Role does not exist."));
    }

    public void createRole(Role role) {
        entityManager.persist(role);
    }
}
