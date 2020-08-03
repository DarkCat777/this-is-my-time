package edu.app.server.controller;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.model.User;
import edu.app.server.service.NonPeriodicTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/nonPeriodicTask")
public class NonPeriodicTaskRestController {
    private final NonPeriodicTaskService nonPeriodicTaskService;

    public NonPeriodicTaskRestController(NonPeriodicTaskService nonPeriodicTaskService) {
        this.nonPeriodicTaskService = nonPeriodicTaskService;
    }

    @GetMapping("/all")
    public List<NonPeriodicTask> findAll() {
        return nonPeriodicTaskService.getAllNonPeriodicTask();
    }

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public NonPeriodicTask newNonPeriodicTask(@Valid @RequestBody NonPeriodicTask nonPeriodicTask) {
        return nonPeriodicTaskService.saveNonPeriodicTask(nonPeriodicTask);
    }

    @PostMapping("/get/{id}")
    public NonPeriodicTask findOne(@PathVariable @Min(1) Long id) {
        return nonPeriodicTaskService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setId(id);
        this.nonPeriodicTaskService.deleteNonPeriodicTask(nonPeriodicTask);
    }
}
