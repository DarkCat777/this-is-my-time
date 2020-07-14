package edu.app.server.service;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.repository.NonPeriodicTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class NonPeriodicTaskService {
    private final NonPeriodicTaskRepository nonPeriodicTaskRepository;

    public NonPeriodicTaskService(NonPeriodicTaskRepository nonPeriodicTaskRepository) {
        this.nonPeriodicTaskRepository = nonPeriodicTaskRepository;
    }

    @Transactional(readOnly = true)
    public NonPeriodicTask getById(Long id) {
        log.info("Buscando tarea no periodica por su id: " + id);
        return this.nonPeriodicTaskRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<NonPeriodicTask> getAllNonPeriodicTask() {
        log.info("Obteniendo todas las tareas no periodicas.");
        return this.nonPeriodicTaskRepository.findAll();
    }

    @Transactional
    public NonPeriodicTask saveNonPeriodicTask(NonPeriodicTask nonPeriodicTask) {
        log.info("Guardando tarea no periodica" + nonPeriodicTask.toString());
        return this.nonPeriodicTaskRepository.save(nonPeriodicTask);
    }

    @Transactional
    public NonPeriodicTask updateNonPeriodicTask(NonPeriodicTask nonPeriodicTask) {
        log.info("Buscando tarea no periodica.");
        NonPeriodicTask oldNonPeriodicTask = this.nonPeriodicTaskRepository.findById(nonPeriodicTask.getId()).orElse(null);
        if (oldNonPeriodicTask != null) {
            log.info("Actualizando tarea no periodica: " + nonPeriodicTask.toString());
            oldNonPeriodicTask.setName(nonPeriodicTask.getName());
            oldNonPeriodicTask.setDescription(nonPeriodicTask.getDescription());
            oldNonPeriodicTask.setState(nonPeriodicTask.getState());
            oldNonPeriodicTask.setUser(nonPeriodicTask.getUser());
            oldNonPeriodicTask.setInitDate(nonPeriodicTask.getInitDate());
            oldNonPeriodicTask.setInitTime(nonPeriodicTask.getInitTime());
            return this.nonPeriodicTaskRepository.save(oldNonPeriodicTask);
        } else {
            log.info("No se encontro una tarea no periodica previa, no se guardan los datos.");
            return null;
        }
    }

    @Transactional
    public String deleteNonPeriodicTask(NonPeriodicTask nonPeriodicTask) {
        NonPeriodicTask oldNonPeriodicTask = this.nonPeriodicTaskRepository.findById(nonPeriodicTask.getId()).orElse(null);
        if (oldNonPeriodicTask == null) {
            log.info("No se borro la tarea no periodica.");
            return "Unsuccessful delete nonPeriodicTask";
        } else {
            log.info("Borrando la tarea no periodica: " + oldNonPeriodicTask.toString());
            this.nonPeriodicTaskRepository.delete(oldNonPeriodicTask);
            return "Success delete nonPeriodicTask";
        }
    }

    @Transactional(readOnly = true)
    public NonPeriodicTask getByName(String name) {
        log.info("Buscando tarea no periodica por su nombre: " + name);
        return this.nonPeriodicTaskRepository.findByName(name).orElse(null);
    }
}
