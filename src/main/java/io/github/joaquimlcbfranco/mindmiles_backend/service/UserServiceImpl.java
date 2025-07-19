package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.InvalidParametersException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.UserAlreadyExistsException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.UserNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        throw new UserNotFoundException("User with username " + username + " not found");
    }

    public User getUserById(long id) {
        Optional<User> foundUser = this.userRepository.findById(id);

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return user;
        }

        throw new UserNotFoundException("User with id " + id + " not found");
    }

    @Override
    public List<User> getUsersBySearch(String searchWords) {
        List<User> foundUsers = this.userRepository.findUserBySearch(searchWords);

        return foundUsers;
    }

    @Override
    @Transactional
    public User addUser(User newUser) {
        this.validateInputs(newUser.getFirstName(), newUser.getLastName(), newUser.getUsername());

        if (this.userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username is in use - " + newUser.getUsername());
        }

        User addedUser = this.userRepository.save(newUser);

        return addedUser;
    }

    @Override
    @Transactional
    public User updateUser(User newUser, long id) {
        this.validateInputs(newUser.getFirstName(), newUser.getLastName(), newUser.getUsername());

        Optional<User> foundUser = this.userRepository.findById(id);

        if (foundUser.isPresent()) {
            User toUpdate = foundUser.get();
            toUpdate.setFirstName(newUser.getFirstName());
            toUpdate.setLastName(newUser.getLastName());
            if (!newUser.getUsername().equals(toUpdate.getUsername()) && this.userRepository.findByUsername(newUser.getUsername()).isPresent()) {
                    throw new UserAlreadyExistsException("Username is in use - " + newUser.getUsername());
            }
            toUpdate.setUsername(newUser.getUsername());
            toUpdate.setEmail(newUser.getEmail());
            toUpdate.setDob(newUser.getDob());
            toUpdate.setGender(newUser.getGender());

            return toUpdate;
        }

        throw new UserNotFoundException("User with id " + id + "not found");
    }

    @Override
    @Transactional
    public String deleteUser(long id) {
        Optional<User> foundUser = this.userRepository.findById(id);

        if (foundUser.isPresent()) {
            User toDelete = foundUser.get();
            this.userRepository.delete(toDelete);
            return "User with id " + toDelete.getId() + " deleted";
        }

        throw new UserNotFoundException("User with id " + id + "not found");
    }

    public boolean validateInputs(String firstName, String lastName, String username) {
        if (firstName == null || !firstName.matches("^[A-Za-z]+$")) {
            throw new InvalidParametersException("First name must be alphabetic");
        }

        if (lastName == null || !lastName.matches("^[A-Za-z]+$")) {
            throw new InvalidParametersException("Last name must be alphabetic");
        }

        if (username == null || !username.matches("^[A-Za-z0-9_\\.]*$") || username.length() < 4) {
            throw new InvalidParametersException("Username must have more than 4 alphanumeric characters");
        }

        return true;
    }

}
