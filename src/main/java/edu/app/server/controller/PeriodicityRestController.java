package edu.app.server.controller;

import edu.app.server.model.Periodicity;
import edu.app.server.service.PeriodicityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Es el controlador Rest de Periodicity y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see PeriodicityService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/periodicity")
public class PeriodicityRestController {
    /**
     * Es el servicio de Periodicity.
     *
     * @see PeriodicityService
     */
    private final PeriodicityService periodicityService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param periodicityService Es el servicio que sera injectado por Spring.
     */
    public PeriodicityRestController(PeriodicityService periodicityService) {
        this.periodicityService = periodicityService;
    }

    /**
     * Es el servicio Rest que devuelve todos las periodicidades mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Periodicity>> findAll() {
        return ResponseEntity.ok(this.periodicityService.getAllPeriodicity());
    }

    /**
     * Es el servicio Rest que crea una nueva periodicidad mediante el metodo PUT.
     *
     * @param periodicity Es la periodicidad que se guardara.
     * @return Es la respuesta que dara el servicio.
     */
    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Periodicity> newPeriodicity(@Valid @RequestBody Periodicity periodicity) {
        return ResponseEntity.ok(this.periodicityService.savePeriodicity(periodicity));
    }

    /**
     * Es el servicio Rest que busca una periodicidad por id su mediante el metodo POST.
     *
     * @param id Es el identificador del periodicity.
     * @return Es la respuesta que dara el servicio.
     */
    @PostMapping("/get/{id}")
    public ResponseEntity<Periodicity> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.periodicityService.getById(id));
    }

    /**
     * Es el servicio Rest que elimina una periodicidad por su mediante el metodo DELETE.
     *
     * @param id Es el identificador del periodicity.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        Periodicity periodicity = new Periodicity();
        periodicity.setId(id);
        this.periodicityService.deletePeriodicity(periodicity);
    }
}
