package edu.app.server.service;

import edu.app.server.model.GroupTask;
import edu.app.server.repository.GroupTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones para los datos del GroupTask.
 *
 * @author Erick David Carpio Hachiri
 * @see edu.app.server.model.Authority
 */
@Log4j2
@Service
public class GroupTaskService {
    /**
     * Es el repositorio de las GroupTask.
     *
     * @see GroupTaskRepository
     */
    private final GroupTaskRepository groupTaskRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param groupTaskRepository Es el repositorio del GroupTask
     * @see GroupTaskRepository
     */
    public GroupTaskService(GroupTaskRepository groupTaskRepository) {
        this.groupTaskRepository = groupTaskRepository;
    }

    /**
     * Obtiene una GroupTask por su identificador, mediante una transacción de solo lectura.
     *
     * @param id Es el indentificador del GroupTask
     * @return Es el groupTask si es que se encuentra y sino retorna un valor nulo.
     * @see Optional
     */
    @Transactional(readOnly = true)
    public GroupTask getById(Long id) {
        log.info("Buscando el grupo de tareas por su id: " + id);
        return this.groupTaskRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los groupTasks, mediante una transacción de solo lectura.
     *
     * @return Es una lista con todas las grouptask.
     */
    @Transactional(readOnly = true)
    public List<GroupTask> getAllGroupTask() {
        log.info("Obteniendo todas las grupo tareas.");
        return this.groupTaskRepository.findAll();
    }

    /**
     * Guarda la groupTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la groupTask que se desea guardar.
     * @return Es la grouptask que se guardo en la BD con su id generada.
     */
    @Transactional
    public GroupTask saveGroupTask(GroupTask periodicTask) {
        log.info("Guardando grupo de tareas" + periodicTask.toString());
        return this.groupTaskRepository.save(periodicTask);
    }

    /**
     * Actualiza la groupTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la groupTask que se desea actualizar.
     * @return Es la grouptask que se guardo en la BD o un nulo si la operación no se concluye con exito.
     */
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

    /**
     * Elimina la groupTask, mediante una transacción de escritura.
     *
     * @param periodicTask Es la groupTask que se desea eliminar.
     * @return Es la cadena que confirma si la operación tuvo exito o no.
     */
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

    /**
     * Obtiene la Grouptask por su nombre, mediante una transaccipon de lectura.
     *
     * @param name Es el nombre de la groupTask que se desea buscar.
     * @return Es la grouptask que se busca o un nulo si no existe.
     */
    @Transactional(readOnly = true)
    public GroupTask getByName(String name) {
        log.info("Buscando el grupo de tareas por su nombre: " + name);
        return this.groupTaskRepository.findByName(name).orElse(null);
    }
}
