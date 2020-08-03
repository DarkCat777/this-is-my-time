package edu.app.server.service;

import edu.app.server.model.PeriodicTask;
import edu.app.server.repository.PeriodicTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PeriodicTaskService {
    private final PeriodicTaskRepository periodicTaskRepository;

    public PeriodicTaskService(PeriodicTaskRepository periodicTaskRepository) {
        this.periodicTaskRepository = periodicTaskRepository;
    }

    @Transactional(readOnly = true)
    public PeriodicTask getById(Long id) {
        log.info("Buscando tarea periodica por su id: " + id);
        return this.periodicTaskRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<PeriodicTask> getAllPeriodicTask() {
        log.info("Obteniendo todas las tareas periodicas.");
        return this.periodicTaskRepository.findAll();
    }

    @Transactional
    public PeriodicTask savePeriodicTask(PeriodicTask periodicTask) {
        log.info("Guardando tarea periodica" + periodicTask.toString());
        return this.periodicTaskRepository.save(periodicTask);
    }

    @Transactional
    public PeriodicTask updatePeriodicTask(PeriodicTask periodicTask) {
        log.info("Buscando tarea periodica.");
        PeriodicTask oldPeriodicTask = this.periodicTaskRepository.findById(periodicTask.getId()).orElse(null);
        if (oldPeriodicTask != null) {
            log.info("Actualizando tarea periodica: " + periodicTask.toString());
            oldPeriodicTask.setName(periodicTask.getName());
            oldPeriodicTask.setDescription(periodicTask.getDescription());
            oldPeriodicTask.setState(periodicTask.getState());
            oldPeriodicTask.setPeriodicities(periodicTask.getPeriodicities());
            oldPeriodicTask.setUser(periodicTask.getUser());
            return this.periodicTaskRepository.save(oldPeriodicTask);
        } else {
            log.info("No se encontro una tarea periodica previa, no se guardan los datos.");
            return null;
        }
    }

    @Transactional
    public String deletePeriodicTask(PeriodicTask periodicTask) {
        PeriodicTask oldPeriodicTask = this.periodicTaskRepository.findById(periodicTask.getId()).orElse(null);
        if (oldPeriodicTask == null) {
            log.info("No se borro la tarea periodica.");
            return "Unsuccessful delete periodicTask";
        } else {
            log.info("Borrando la tarea periodica: " + oldPeriodicTask.toString());
            this.periodicTaskRepository.delete(oldPeriodicTask);
            return "Success delete periodicTask";
        }
    }

    @Transactional(readOnly = true)
    public PeriodicTask getByName(String name) {
        log.info("Buscando tarea periodica por su nombre: " + name);
        return this.periodicTaskRepository.findByName(name).orElse(null);
    }
}
