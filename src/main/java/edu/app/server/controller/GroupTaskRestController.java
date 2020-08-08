package edu.app.server.controller;

import edu.app.server.model.GroupTask;
import edu.app.server.service.AuthorityService;
import edu.app.server.service.GroupTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Es el controlador Rest de GroupTask y admite conexiones con cualquier IP.
 *
 * @author Erick David Carpio Hachiri
 * @see GroupTaskService
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/groupTask")
public class GroupTaskRestController {
    /**
     * Es el servicio de GroupTask.
     *
     * @see GroupTaskService
     */
    private final GroupTaskService groupTaskService;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param groupTaskService Es el servicio que sera injectado por Spring.
     */
    public GroupTaskRestController(GroupTaskService groupTaskService) {
        this.groupTaskService = groupTaskService;
    }

    /**
     * Es el servicio Rest que devuelve todos los groupTask mediante el metodo GET.
     *
     * @return Es la respuesta que dara el servicio.
     */
    @GetMapping("/all")
    public ResponseEntity<List<GroupTask>> findAll() {
        return ResponseEntity.ok(this.groupTaskService.getAllGroupTask());
    }

    /**
     * Es el servicio Rest que crea una nueva groupTask mediante el metodo PUT.
     *
     * @param groupTask Es el groupTask que se guardara.
     * @return Es la respuesta que dara el servicio.
     */
    @PutMapping("/new")
    public ResponseEntity<GroupTask> newGroupTask(@Valid @RequestBody GroupTask groupTask) {
        return ResponseEntity.ok(this.groupTaskService.saveGroupTask(groupTask));
    }

    /**
     * Es el servicio Rest que busca un groupTask por id su mediante el metodo POST.
     *
     * @param id Es el identificador del groupTask.
     * @return Es la respuesta que dara el servicio.
     */
    @PostMapping("/get/{id}")
    public ResponseEntity<GroupTask> findOne(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(this.groupTaskService.getById(id));
    }

    /**
     * Es el servicio Rest que elimina un groupTask por su mediante el metodo DELETE.
     *
     * @param id Es el identificador del GroupTask.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOne(@PathVariable @Min(1) Long id) {
        GroupTask groupTask = new GroupTask();
        groupTask.setId(id);
        this.groupTaskService.deleteGroupTask(groupTask);
    }
}
