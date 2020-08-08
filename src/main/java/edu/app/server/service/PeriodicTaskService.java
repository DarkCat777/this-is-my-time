package edu.app.server.service;

import edu.app.server.model.PeriodicTask;
import edu.app.server.repository.AuthorityRepository;
import edu.app.server.repository.PeriodicTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones a los datos de PeriodicTask.
 *
 * @author Erick David Carpio Hachiri
 * @see PeriodicTask
 */
@Log4j2
@Service
public class PeriodicTaskService {
    /**
     * Es el repositorio de PeriodicTask.
     *
     * @see AuthorityRepository
     */
    private final PeriodicTaskRepository periodicTaskRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param periodicTaskRepository Es el repositorio que sera injectado por Spring.
     */
    public PeriodicTaskService(PeriodicTaskRepository periodicTaskRepository) {
        this.periodicTaskRepository = periodicTaskRepository;
    }

    /**
     * Obtiene la periodicTask mediante su id, mediante una transacción de solo lectura.
     *
     * @param id Es el id del PeriodicTask.
     * @return Retorna el valor existe o no existe que es retornado por el repositorio.
     * @see PeriodicTaskRepository
     */
    @Transactional(readOnly = true)
    public PeriodicTask getById(Long id) {
        log.info("Buscando tarea periodica por su id: " + id);
        return this.periodicTaskRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las periodicTask, mediante una transacción de solo lectura.
     *
     * @return Son todas las periodicTask realizadas por el repositorio.
     * @see PeriodicTaskRepository
     */
    @Transactional(readOnly = true)
    public List<PeriodicTask> getAllPeriodicTask() {
        log.info("Obteniendo todas las tareas periodicas.");
        return this.periodicTaskRepository.findAll();
    }

    /**
     * Guarda la periodicTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la autoridad a guardar.
     * @return Es la periodicTask con el id asignado en la base de datos.
     * @see PeriodicTaskRepository
     */
    @Transactional
    public PeriodicTask savePeriodicTask(PeriodicTask periodicTask) {
        log.info("Guardando tarea periodica" + periodicTask.toString());
        return this.periodicTaskRepository.save(periodicTask);
    }

    /**
     * Actualiza la periodicTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la periodicTask a actualizar.
     * @return Es la periodicTask con el los valores actualizados en la base de datos.
     * @see PeriodicTaskRepository
     */
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

    /**
     * Elimina la periodicTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la periodictTask a eliminar.
     * @return Es una cadena con la confirnación.
     * @see PeriodicTaskRepository
     */
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

    /**
     * Realiza la busqueda por nombre, mediante una transacción de solo lectura.
     *
     * @param name Es el nombre de la periodicTask.
     * @return Es la periodicTask que se busco.
     * @see PeriodicTaskRepository
     */
    @Transactional(readOnly = true)
    public PeriodicTask getByName(String name) {
        log.info("Buscando tarea periodica por su nombre: " + name);
        return this.periodicTaskRepository.findByName(name).orElse(null);
    }
}
