package io.github.joaquimlcbfranco.mindmiles_backend.controller;

import io.github.joaquimlcbfranco.mindmiles_backend.dto.LoginRequest;
import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.BadCredentialsException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.UserNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            boolean isAuthenticated = this.userService.autheticate(loginRequest.getUsername(), loginRequest.getPassword());

            if (isAuthenticated) {
                session.setAttribute("user", loginRequest.getUsername());
                return ResponseEntity.ok("Login was successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (UserNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred" + e);
        }
    }
}
