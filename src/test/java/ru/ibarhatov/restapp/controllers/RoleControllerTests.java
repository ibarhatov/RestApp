package ru.ibarhatov.restapp.controllers;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.ibarhatov.restapp.controller.RoleController;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;
import ru.ibarhatov.restapp.services.RoleService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class RoleControllerTests extends Assert {

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;

    static Role role;
    private final static Integer ROLE_ID = 42;

    @BeforeClass
    public static void init() {
        RoleCode createdRoleCode = RoleCode.ADMIN;
        String createdRoleName = createdRoleCode.getDescription();

        role = new Role();
        role.setRoleCode(createdRoleCode);
        role.setName(createdRoleName);
    }

    @AfterClass
    public static void clean() {
        role = null;
    }

    @Test
    public void successfulCreatingRoleTest() {
        when(roleService.create(role)).thenReturn(ROLE_ID);

        Integer createdRole = roleController.createRole(role);

        assertEquals(createdRole, ROLE_ID);
    }

    @Test
    public void successfulFindingRoleTest() {
        when(roleService.findOne(role.getRoleCode())).thenReturn(role);

        String foundedRole = roleController.findRole(role.getRoleCode());

        assertEquals(foundedRole, role.getName());
    }

    @Test
    public void successfulUpdatingRoleTest() {
        when(roleService.update(role)).thenReturn(ROLE_ID);

        Integer updatedRole = roleController.updateRole(role);

        assertEquals(updatedRole, ROLE_ID);
    }

    @Test
    public void successfulDeletingRoleTest() {
        roleController.deleteRole(ROLE_ID);

        verify(roleService).delete(ROLE_ID);
    }
}
