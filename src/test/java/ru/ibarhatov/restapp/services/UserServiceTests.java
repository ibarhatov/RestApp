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
import ru.ibarhatov.restapp.model.User;
import ru.ibarhatov.restapp.repository.UserRepository;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests extends Assert {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init() {
        String createdUserCode = "admin";
        String createdUserName = createdUserCode;

        User user = new User();
        user.setLogin(createdUserCode);
        user.setName(createdUserName);
        userRepository.save(user);
    }

    @After
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    public void successfulCreatingUserTest() {
        userRepository.deleteAll();

        String createdUserCode = "admin";
        String createdUserName = createdUserCode;
        assertNull(userRepository.findByLogin(createdUserCode));

        User user = new User();
        user.setLogin(createdUserCode);
        user.setName(createdUserName);
        Integer savedId = userService.create(user);

        Optional<User> resultUser = userRepository.findById(savedId);

        assertTrue(resultUser.isPresent());
        assertEquals(createdUserCode, resultUser.get().getLogin());
        assertEquals(createdUserName, resultUser.get().getName());
    }

    @Test
    public void successfulFindingUserTest() {
        User existUser = userRepository.findAll().get(0);
        assertNotNull(existUser);
        User receivedUser = userService.findOne(existUser.getLogin());

        assertEquals(existUser.getUserId(), receivedUser.getUserId());
        assertEquals(existUser.getLogin(), receivedUser.getLogin());
        assertEquals(existUser.getName(), receivedUser.getName());
    }

    @Test
    public void successfulUpdatingUserTest() {
        String updatedUserCode = "user";
        String updatedUserName = updatedUserCode;
        assertNull(userRepository.findByLogin(updatedUserCode));

        User existUser = userRepository.findAll().get(0);

        existUser.setLogin(updatedUserCode);
        existUser.setName(updatedUserName);
        userService.update(existUser);

        User resultUser = userRepository.findByLogin(updatedUserCode);

        assertNotNull(resultUser);
        assertEquals(updatedUserCode, resultUser.getLogin());
        assertEquals(updatedUserName, resultUser.getName());
        assertNotNull(resultUser.getDateModified());
        assertNotEquals(existUser.getDateModified(), resultUser.getDateModified());
    }

    @Test
    public void successfulDeletingUserTest() {
        User existUser = userRepository.findAll().get(0);

        userService.delete(existUser.getUserId());

        assertFalse(userRepository.existsById(existUser.getUserId()));
    }
}
