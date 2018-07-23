package ru.ibarhatov.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleCode(RoleCode roleCode);
}
