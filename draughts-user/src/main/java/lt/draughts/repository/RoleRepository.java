package lt.draughts.repository;

import lt.draughts.entities.Role;

public interface RoleRepository {
    Role findByName(String name);

    void createRole(Role role);
}
