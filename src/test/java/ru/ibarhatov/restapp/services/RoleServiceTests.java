package ru.ibarhatov.restapp.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;
import ru.ibarhatov.restapp.repository.RoleRepository;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RoleServiceTests extends Assert {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void init() {
        RoleCode createdRoleCode = RoleCode.ADMIN;
        String createdRoleName = createdRoleCode.getDescription();

        Role role = new Role();
        role.setRoleCode(createdRoleCode);
        role.setName(createdRoleName);
        roleRepository.save(role);
    }

    @After
    public void clean() {
        roleRepository.deleteAll();
    }

    @Test
    public void successfulCreatingRoleTest() {
        roleRepository.deleteAll();

        RoleCode createdRoleCode = RoleCode.ADMIN;
        String createdRoleName = createdRoleCode.getDescription();
        assertNull(roleRepository.findByRoleCode(createdRoleCode));

        Role role = new Role();
        role.setRoleCode(createdRoleCode);
        role.setName(createdRoleName);
        Integer savedId = roleService.create(role);

        Optional<Role> resultRole = roleRepository.findById(savedId);

        assertTrue(resultRole.isPresent());
        assertEquals(createdRoleCode, resultRole.get().getRoleCode());
        assertEquals(createdRoleName, resultRole.get().getName());
    }

    @Test
    public void successfulFindingRoleTest() {
        Role existRole = roleRepository.findAll().get(0);
        assertNotNull(existRole);
        Role receivedRole = roleService.findOne(existRole.getRoleCode());

        assertEquals(existRole.getRoleId(), receivedRole.getRoleId());
        assertEquals(existRole.getRoleCode(), receivedRole.getRoleCode());
        assertEquals(existRole.getName(), receivedRole.getName());
    }

    @Test
    public void successfulUpdatingRoleTest() {
        RoleCode updatedRoleCode = RoleCode.MANAGER;
        String updatedRoleName = updatedRoleCode.name();
        assertNull(roleRepository.findByRoleCode(updatedRoleCode));

        Role existRole = roleRepository.findAll().get(0);

        existRole.setRoleCode(updatedRoleCode);
        existRole.setName(updatedRoleName);
        roleService.update(existRole);

        Role resultRole = roleRepository.findByRoleCode(updatedRoleCode);

        assertNotNull(resultRole);
        assertEquals(updatedRoleCode, resultRole.getRoleCode());
        assertEquals(updatedRoleName, resultRole.getName());
        assertNotNull(resultRole.getDateModified());
        assertNotEquals(existRole.getDateModified(), resultRole.getDateModified());
    }

    @Test
    public void successfulDeletingRoleTest() {
        Role existRole = roleRepository.findAll().get(0);

        roleService.delete(existRole.getRoleId());

        assertFalse(roleRepository.existsById(existRole.getRoleId()));
    }
}
