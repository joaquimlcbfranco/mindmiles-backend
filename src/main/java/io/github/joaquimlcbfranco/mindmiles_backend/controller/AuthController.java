package io.github.joaquimlcbfranco.mindmiles_backend.controller;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }
}
