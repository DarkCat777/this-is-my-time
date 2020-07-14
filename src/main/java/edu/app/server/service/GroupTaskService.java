package edu.app.server.service;

import edu.app.server.model.GroupTask;
import edu.app.server.repository.GroupTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class GroupTaskService {

    private final GroupTaskRepository groupTaskRepository;

    public GroupTaskService(GroupTaskRepository groupTaskRepository) {
        this.groupTaskRepository = groupTaskRepository;
    }

    @Transactional(readOnly = true)
    public GroupTask getById(Long id) {
        log.info("Buscando el grupo de tareas por su id: " + id);
        return this.groupTaskRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<GroupTask> getAllGroupTask() {
        log.info("Obteniendo todas las grupo tareas.");
        return this.groupTaskRepository.findAll();
    }

    @Transactional
    public GroupTask saveGroupTask(GroupTask periodicTask) {
        log.info("Guardando grupo de tareas" + periodicTask.toString());
        return this.groupTaskRepository.save(periodicTask);
    }

    @Transactional
    public GroupTask updateGroupTask(GroupTask periodicTask) {
        log.info("Buscando tarea grupo de tareas.");
        GroupTask oldGroupTask = this.groupTaskRepository.findById(periodicTask.getId()).orElse(null);
        if (oldGroupTask != null) {
            log.info("Actualizando grupo de tareas: " + periodicTask.toString());
            oldGroupTask.setName(periodicTask.getName());
            oldGroupTask.setUser(periodicTask.getUser());
            oldGroupTask.setTasks(periodicTask.getTasks());
            return this.groupTaskRepository.save(oldGroupTask);
        } else {
            log.info("No se encontro un grupo de tareas, no se guardan los datos.");
            return null;
        }
    }

    @Transactional
    public String deleteGroupTask(GroupTask periodicTask) {
        GroupTask oldGroupTask = this.groupTaskRepository.findById(periodicTask.getId()).orElse(null);
        if (oldGroupTask == null) {
            log.info("No se borro el grupo de tareas.");
            return "Unsuccessful delete groupTask";
        } else {
            log.info("Borrando el grupo de tareas: " + oldGroupTask.toString());
            this.groupTaskRepository.delete(oldGroupTask);
            return "Success delete groupTask";
        }
    }

    @Transactional(readOnly = true)
    public GroupTask getByName(String name) {
        log.info("Buscando el grupo de tareas por su nombre: " + name);
        return this.groupTaskRepository.findByName(name).orElse(null);
    }
}
