package ru.ibarhatov.restapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibarhatov.restapp.model.User;
import ru.ibarhatov.restapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Integer save(User user) {
        User result = userRepository.save(user);
        return result.getUserId();
    }

    @Override
    public Integer create(User user) {
        return save(user);
    }

    @Override
    public User findOne(String login) {
        User result = userRepository.findByLogin(login);
        return result;
    }

    @Override
    public Integer update(User user) {
        return save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
