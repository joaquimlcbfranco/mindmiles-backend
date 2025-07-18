package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> foundUser = this.userRepository.findByUsername(username);

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return user;
        }

        return null;
    }

    @Override
    public List<User> getUsersBySearch(String searchWords) {
        List<User> foundUsers = this.userRepository.findUserBySearch(searchWords);

        return foundUsers;
    }

    @Override
    public User addUser(User newUser) {
        if (this.userRepository.findAll().contains(newUser.getUsername().toLowerCase())) {
            return null;
        }

    }
}
