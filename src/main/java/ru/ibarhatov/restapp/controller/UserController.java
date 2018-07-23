package ru.ibarhatov.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ibarhatov.restapp.model.User;
import ru.ibarhatov.restapp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final String createCommand = "/create";
    private final String findCommand = "/find";
    private final String updateCommand = "/update";
    private final String deleteCommand = "/delete";

    @Autowired
    UserService userService;

    @PutMapping(createCommand)
    public Integer createUser(@RequestBody User user) {
        Integer id = userService.create(user);
        return id;
    }

    @GetMapping(findCommand)
    public String findUser(@RequestParam(name = "code") String login) {
        User result = userService.findOne(login);
        return result != null ? result.getName() : "Not found.";
    }

    @PostMapping(updateCommand)
    public Integer updateUser(@RequestBody User user) {
        Integer result = userService.update(user);
        return result;
    }

    @DeleteMapping(deleteCommand)
    public void deleteUser(@RequestParam(name = "id") Integer id) {
        userService.delete(id);
    }
}