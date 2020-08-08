package edu.app.server.controller;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.service.NonPeriodicTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Es el controlador Rest de NonPeriodicTask y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see NonPeriodicTaskService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/nonPeriodicTask")
public class NonPeriodicTaskRestController {
    /**
     * Es el servicio de NonPeriodicTask.
     *
     * @see NonPeriodicTaskService
     */
    private final NonPeriodicTaskService nonPeriodicTaskService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param nonPeriodicTaskService Es el servicio que sera injectado por Spring.
     */
    public NonPeriodicTaskRestController(NonPeriodicTaskService nonPeriodicTaskService) {
        this.nonPeriodicTaskService = nonPeriodicTaskService;
    }

    /**
     * Es el servicio Rest que devuelve todos las nonPeriodicTask mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @GetMapping("/all")
    public ResponseEntity<List<NonPeriodicTask>> findAll() {
        return ResponseEntity.ok(this.nonPeriodicTaskService.getAllNonPeriodicTask());
    }

    /**
     * Es el servicio Rest que crea una nueva nonPeriodicTask mediante el metodo PUT.
     *
     * @param nonPeriodicTask Es el nonPeriodicTask que se guardara.
     * @return Es la respuesta que dara el servicio.
     */
    @PutMapping("/new")
    public ResponseEntity<NonPeriodicTask> newNonPeriodicTask(@Valid @RequestBody NonPeriodicTask nonPeriodicTask) {
        return ResponseEntity.ok(nonPeriodicTaskService.saveNonPeriodicTask(nonPeriodicTask));
    }

    /**
     * Es el servicio Rest que busca una nonPeriodicTask por id su mediante el metodo POST.
     *
     * @param id Es el identificador del nonPeriodicTask.
     * @return Es la respuesta que dara el servicio.
     */
    @PostMapping("/get/{id}")
    public ResponseEntity<NonPeriodicTask> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.nonPeriodicTaskService.getById(id));
    }

    /**
     * Es el servicio Rest que elimina una nonPeriodicTask por su mediante el metodo DELETE.
     *
     * @param id Es el identificador del nonPeriodicTask.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setId(id);
        this.nonPeriodicTaskService.deleteNonPeriodicTask(nonPeriodicTask);
    }
}
