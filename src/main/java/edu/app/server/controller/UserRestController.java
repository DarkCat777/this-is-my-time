package edu.app.server.controller;

import edu.app.server.model.User;
import edu.app.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserRestController {
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable @Min(1) Long id) {
        return userService.getById(id);
    }

}
