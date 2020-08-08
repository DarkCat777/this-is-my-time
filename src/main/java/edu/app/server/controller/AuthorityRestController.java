package edu.app.server.controller;

import edu.app.server.model.Authority;
import edu.app.server.service.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Es el controlador Rest de Authority y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see AuthorityService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/authority")
public class AuthorityRestController {
    /**
     * Es el servicio de Authority.
     *
     * @see AuthorityService
     */
    private final AuthorityService authorityService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param authorityService Es el servicio que sera injectado por Spring.
     */
    public AuthorityRestController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * Es el servicio Rest que devuelve todos las autoridades mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Authority>> getAll() {
        return ResponseEntity.ok(this.authorityService.getAllAuthorities());
    }

    /**
     * Es el servicio Rest que crea una nueva autoridad mediante el metodo PUT.
     *
     * @param authority Es la autoridad que se guardara.
     * @return Es la respuesta que dara el servicio.
     */
    @PutMapping("/new")
    public ResponseEntity<Authority> newAuthority(@Valid @RequestBody Authority authority) {
        return ResponseEntity.ok(this.authorityService.saveAuthority(authority));
    }

    /**
     * Es el servicio Rest que busca una autoridad por id su mediante el metodo POST.
     *
     * @param id Es el identificador con el que se buscara la autoridad.
     * @return Es la respuesta que dara el servicio.
     */
    @PostMapping("/get/{id}")
    public ResponseEntity<Authority> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.authorityService.getById(id));
    }

    /**
     * Es el servicio Rest que elimina una autoridad mediante el metodo DELETE.
     *
     * @param id Es el identificador de la autoridad.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        Authority authority = new Authority();
        authority.setId(id);
        this.authorityService.deleteAuthority(authority);
    }

}
