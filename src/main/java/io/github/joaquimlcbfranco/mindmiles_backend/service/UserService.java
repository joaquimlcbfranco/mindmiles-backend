package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface UserService {

    List<User> getUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    List<User> getUsersBySearch(String searchWords);

    User addUser(User newUser);

    User updateUser(User newUser, long id);

    String deleteUser(long id);
}
