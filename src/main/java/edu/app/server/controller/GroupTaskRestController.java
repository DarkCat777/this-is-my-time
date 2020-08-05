package edu.app.server.controller;

import edu.app.server.model.GroupTask;
import edu.app.server.service.GroupTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/groupTask")
public class GroupTaskRestController {
    private final GroupTaskService groupTaskService;

    public GroupTaskRestController(GroupTaskService groupTaskService) {
        this.groupTaskService = groupTaskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GroupTask>> findAll() {
        return ResponseEntity.ok(this.groupTaskService.getAllGroupTask());
    }

    @PutMapping("/new")
    public ResponseEntity<GroupTask> newGroupTask(@Valid @RequestBody GroupTask groupTask) {
        return ResponseEntity.ok(this.groupTaskService.saveGroupTask(groupTask));
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<GroupTask> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.groupTaskService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        GroupTask groupTask = new GroupTask();
        groupTask.setId(id);
        this.groupTaskService.deleteGroupTask(groupTask);
    }
}
