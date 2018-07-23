package ru.ibarhatov.restapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;
import ru.ibarhatov.restapp.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    private Integer save(Role role) {
        Role result = roleRepository.save(role);
        return result.getRoleId();
    }

    @Override
    public Integer create(Role role) {
        return save(role);
    }

    @Override
    public Role findOne(RoleCode roleCode) {
        Role result = roleRepository.findByRoleCode(roleCode);
        return result;
    }

    @Override
    public Integer update(Role role) {
        return save(role);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
