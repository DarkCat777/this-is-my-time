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

/**
 * Es el controlador Rest de User y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see UserService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserRestController {
    /**
     * Es el servicio de User.
     *
     * @see UserService
     */
    private final UserService userService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param userService Es el servicio que sera injectado por Spring.
     */
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Es el servicio Rest que devuelve todos los usuarios mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @PreAuthorize("hasAuthority('Administrator')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Es el servicio Rest que crea un nuevo usuario mediante el metodo PUT.
     *
     * @param user Es el usuario que se guardara en la base de datos.
     * @return Es la respuesta que dara el servicio.
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping("/new")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = this.userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Es el servicio Rest que elimina un usuario, mediante el metodo DELETE.
     *
     * @param id Es el id del usuario que se quiere eliminar.
     */
    @PreAuthorize("hasAuthority('Administrator')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        User user = new User();
        user.setId(id);
        this.userService.deleteUser(user);
    }
}
