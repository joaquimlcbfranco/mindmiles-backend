package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    List<User> getUsersBySearch(String searchWords);

    User addUser(User newUser);

    User updateUser(User newUser, long id);

    String deleteUser(long id);

    boolean autheticate(String username, String password);
}
