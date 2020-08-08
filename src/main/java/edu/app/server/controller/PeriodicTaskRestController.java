package edu.app.server.controller;

import edu.app.server.model.PeriodicTask;
import edu.app.server.service.PeriodicTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Es el controlador Rest de PeriodicTask y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see PeriodicTaskService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/periodicTask")
public class PeriodicTaskRestController {
    /**
     * Es el servicio de PeriodicTask.
     *
     * @see PeriodicTaskService
     */
    private final PeriodicTaskService periodicTaskService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param periodicTaskService Es el servicio que sera injectado por Spring.
     */
    public PeriodicTaskRestController(PeriodicTaskService periodicTaskService) {
        this.periodicTaskService = periodicTaskService;
    }

    /**
     * Es el servicio Rest que devuelve todos las periodicTask mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @GetMapping("/all")
    public ResponseEntity<List<PeriodicTask>> findAll() {
        return ResponseEntity.ok(this.periodicTaskService.getAllPeriodicTask());
    }

    /**
     * Es el servicio Rest que crea una nueva periodicTask mediante el metodo PUT.
     *
     * @param periodicTask Es el peridicTask que se guardara.
     * @return Es la respuesta que dara el servicio.
     */
    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PeriodicTask> newPeriodicTask(@Valid @RequestBody PeriodicTask periodicTask) {
        return ResponseEntity.ok(this.periodicTaskService.savePeriodicTask(periodicTask));
    }

    /**
     * Es el servicio Rest que busca una PeriodicTask por id su mediante el metodo POST.
     *
     * @param id Es el identificador de el periodicTask.
     * @return Es la respuesta que dara el servicio.
     */
    @PostMapping("/get/{id}")
    public ResponseEntity<PeriodicTask> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.periodicTaskService.getById(id));
    }

    /**
     * Es el servicio Rest que elimina una PeriodicTask por su mediante el metodo DELETE.
     *
     * @param id Es el identificador de el periodicTask.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setId(id);
        this.periodicTaskService.deletePeriodicTask(periodicTask);
    }
}
