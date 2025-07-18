package io.github.joaquimlcbfranco.mindmiles_backend.controller;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.InvalidParametersException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.UserNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.service.UserService;
import io.github.joaquimlcbfranco.mindmiles_backend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        return this.userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        return this.userService.deleteUser(id);
    }

}
