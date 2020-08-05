package edu.app.server.repository;

import edu.app.server.model.PeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio de Periodictask el cual implementa las operaciones de JpaRepository y otras mas personalizadas.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see PeriodicTask
 */
@Repository
public interface PeriodicTaskRepository extends JpaRepository<PeriodicTask, Long> {
    /**
     * Obtiene la PeriodicTask por su id.
     *
     * @param id Es la id del registro en la base de datos de la entidad PeriodicTask
     * @return Retorna un valor que puede o no puede encontrarse.
     * @see Optional
     */
    Optional<PeriodicTask> getById(Long id);

    /**
     * Obtiene la PeriodicTask por su nombre.
     *
     * @param name Es el nombre del PeriodicTask
     * @return Retorna un valor que puede o no puede encontrarse.
     * @see Optional
     */
    Optional<PeriodicTask> findByName(String name);
}
