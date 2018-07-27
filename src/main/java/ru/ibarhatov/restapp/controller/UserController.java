package ru.ibarhatov.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ibarhatov.restapp.model.User;
import ru.ibarhatov.restapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Integer createUser(@RequestBody User user) {
        Integer id = userService.create(user);
        return id;
    }

    @GetMapping
    public String findUser(@RequestParam(name = "code") String login) {
        User result = userService.findOne(login);
        return result != null ? result.getName() : "Not found.";
    }

    @PutMapping
    public Integer updateUser(@RequestBody User user) {
        Integer result = userService.update(user);
        return result;
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "id") Integer id) {
        userService.delete(id);
    }
}