package edu.app.server.service;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.repository.NonPeriodicTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Es el modulo del servicio que implementa las reglas de negocio o transformaciones a los datos de NonPeriodicTask.
 *
 * @author Erick David Carpio Hachiri
 * @see NonPeriodicTask
 */
@Log4j2
@Service
public class NonPeriodicTaskService {
    /**
     * Es el repositorio de NonPeriodicTask.
     */
    private final NonPeriodicTaskRepository nonPeriodicTaskRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param nonPeriodicTaskRepository Es el repositorio de NonPeriodicTask.
     */
    public NonPeriodicTaskService(NonPeriodicTaskRepository nonPeriodicTaskRepository) {
        this.nonPeriodicTaskRepository = nonPeriodicTaskRepository;
    }

    /**
     * Obtiene la nonPeriodicTask por identificador, mediante una transacción de lectura.
     *
     * @param id Es el identificador de la nonPeriodicTask que se desea obtener.
     * @return Es la nonPeriodicTask que se desea obtener o un valor nulo si no se encuentra.
     */
    @Transactional(readOnly = true)
    public NonPeriodicTask getById(Long id) {
        log.info("Buscando tarea no periodica por su id: " + id);
        return this.nonPeriodicTaskRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las nonPeriodicTask, mediante una transacción de lectura.
     *
     * @return Es una lista con todas las NonPeriodicTasks.
     */
    @Transactional(readOnly = true)
    public List<NonPeriodicTask> getAllNonPeriodicTask() {
        log.info("Obteniendo todas las tareas no periodicas.");
        return this.nonPeriodicTaskRepository.findAll();
    }

    /**
     * Guarda la NonPeriodicTask, mediante una transacción de escritura.
     *
     * @param nonPeriodicTask Es la nonPeriodicTask que se desea guardar.
     * @return Es la nonPeriodicTask que esta en la BD y con el id actualizado.
     */
    @Transactional
    public NonPeriodicTask saveNonPeriodicTask(NonPeriodicTask nonPeriodicTask) {
        log.info("Guardando tarea no periodica" + nonPeriodicTask.toString());
        return this.nonPeriodicTaskRepository.save(nonPeriodicTask);
    }

    /**
     * Actualiza la NonPeriodicTask, mediante una transacción de escritura.
     *
     * @param nonPeriodicTask Es la nonPeriodicTask que se desea actualizar.
     * @return Es la nonPeriodicTask que esta en la BD o un valor nulo si no se completo la operación.
     */
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

    /**
     * Elimina la NonPeriodicTask, mediante una transacción de escritura.
     *
     * @param nonPeriodicTask Es la nonPeriodicTask que se desea eliminar.
     * @return Retorna una cadena que confirma que se completo la operación.
     */
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

    /**
     * Obtiene una NonPeriodicTask por su nombre, mediante una transacción de escritura.
     *
     * @param name Es nombre de la NonPeriodicTask
     * @return Es la nonPeriodicTask que esta en la BD o un valor nulo si no se completo la operación.
     */
    @Transactional(readOnly = true)
    public NonPeriodicTask getByName(String name) {
        log.info("Buscando tarea no periodica por su nombre: " + name);
        return this.nonPeriodicTaskRepository.findByName(name).orElse(null);
    }
}
