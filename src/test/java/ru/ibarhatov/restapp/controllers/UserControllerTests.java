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
import ru.ibarhatov.restapp.controller.UserController;
import ru.ibarhatov.restapp.model.User;
import ru.ibarhatov.restapp.services.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class UserControllerTests extends Assert {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    static User user;
    private final static Integer USER_ID = 42;

    @BeforeClass
    public static void init() {
        String createdUserCode = "admin";
        String createdUserName = createdUserCode;

        user = new User();
        user.setLogin(createdUserCode);
        user.setName(createdUserName);
    }

    @AfterClass
    public static void clean() {
        user = null;
    }

    @Test
    public void successfulCreatingUserTest() {
        when(userService.create(user)).thenReturn(USER_ID);

        Integer createdUser = userController.createUser(user);

        assertEquals(createdUser, USER_ID);
    }

    @Test
    public void successfulFindingUserTest() {
        when(userService.findOne(user.getLogin())).thenReturn(user);

        String foundedUser = userController.findUser(user.getLogin());

        assertEquals(foundedUser, user.getName());
    }

    @Test
    public void successfulUpdatingUserTest() {
        when(userService.update(user)).thenReturn(USER_ID);

        Integer updatedUser = userController.updateUser(user);

        assertEquals(updatedUser, USER_ID);
    }

    @Test
    public void successfulDeletingUserTest() {
        userController.deleteUser(USER_ID);

        verify(userService).delete(USER_ID);
    }
}
