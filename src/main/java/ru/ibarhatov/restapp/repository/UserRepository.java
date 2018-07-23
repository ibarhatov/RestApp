package ru.ibarhatov.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibarhatov.restapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
