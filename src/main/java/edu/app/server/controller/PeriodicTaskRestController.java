package edu.app.server.controller;

import edu.app.server.model.PeriodicTask;
import edu.app.server.service.PeriodicTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/periodicTask")
public class PeriodicTaskRestController {
    private final PeriodicTaskService periodicTaskService;

    public PeriodicTaskRestController(PeriodicTaskService periodicTaskService) {
        this.periodicTaskService = periodicTaskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PeriodicTask>> findAll() {
        return ResponseEntity.ok(this.periodicTaskService.getAllPeriodicTask());
    }

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PeriodicTask> newPeriodicTask(@Valid @RequestBody PeriodicTask periodicTask) {
        return ResponseEntity.ok(this.periodicTaskService.savePeriodicTask(periodicTask));
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<PeriodicTask> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.periodicTaskService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setId(id);
        this.periodicTaskService.deletePeriodicTask(periodicTask);
    }
}
