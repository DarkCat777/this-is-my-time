package edu.app.server.repository;

import edu.app.server.model.NonPeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio de NonPeriodicTask el cual hace uso de JpaRepository para importar varios metodos de intercambio con la base de datos.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see NonPeriodicTask
 */
@Repository
public interface NonPeriodicTaskRepository extends JpaRepository<NonPeriodicTask, Long> {
    /**
     * Busca la NonPeriodicTask por su id.
     *
     * @param id Es el id del registro que se desea buscar.
     * @return Retorna un valor opcional que puede estar o no. (null or not null)
     * @see Optional
     */
    Optional<NonPeriodicTask> getById(Long id);

    /**
     * Busca la NonPeriodicTask por su nombre.
     *
     * @param name Es el nombre de la NonPeriodicTask.
     * @return Retorna un valor opcional que puede estar o no. (null or not null)
     * @see Optional
     */
    Optional<NonPeriodicTask> findByName(String name);
}
