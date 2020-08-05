package edu.app.server.repository;

import edu.app.server.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio de Resource el cual implementa las operaciones de JpaRepository y algunas personalizadas.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see Resource
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    /**
     * Obtiene el recurso por su nombre.
     *
     * @param resource Es eñ nombre del recurso.
     * @return Retorna un valor que puede o no existir.
     * @see Optional
     */
    Optional<Resource> findByResource(String resource);
}
