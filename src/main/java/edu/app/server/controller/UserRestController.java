package edu.app.server.controller;

import edu.app.server.model.User;
import edu.app.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @PutMapping("/new")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = this.userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PreAuthorize("hasAuthority('Administrator')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        User user = new User();
        user.setId(id);
        this.userService.deleteUser(user);
    }
}
