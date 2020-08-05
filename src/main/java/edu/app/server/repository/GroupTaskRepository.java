package edu.app.server.repository;

import edu.app.server.model.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio de GroupTask el cual se encarga de realizar las operaciones de la base de datos.
 * Hace uso de JpaRepository para poder extender ciertos metodos ya programados.
 *
 * @see JpaRepository
 * @see GroupTask
 * @author Erick David Carpio Hachiri
 */
@Repository
public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {
    /**
     * Busca un GroupTask por su id.
     *
     * @param id Es el id del registro del cual queremos los datos.
     * @return Es un valor opcional puede o no puede estar.(null or notnull)
     * @see Optional
     */
    Optional<GroupTask> getById(Long id);

    /**
     * Busca un GroupTask por su nombre.
     *
     * @param name Es el nombre del GroupTask que se desea buscar.
     * @return Es un valor opcional puede o no puede estar.(null or notnull)
     * @see Optional
     */
    Optional<GroupTask> findByName(String name);
}
