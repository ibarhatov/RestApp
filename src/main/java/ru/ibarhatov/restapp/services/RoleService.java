package ru.ibarhatov.restapp.services;

import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;

public interface RoleService {

    Integer create(Role role);

    Role findOne(RoleCode roleCode);

    Integer update(Role role);

    void delete(Integer id);
}
