package ru.ibarhatov.restapp.services;

import ru.ibarhatov.restapp.model.User;

public interface UserService {

    Integer create(User user);

    User findOne(String login);

    Integer update(User user);

    void delete(Integer id);
}
