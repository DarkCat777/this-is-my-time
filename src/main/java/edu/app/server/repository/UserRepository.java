package edu.app.server.repository;

import edu.app.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio de User el cual implementa las operaciones de JpaRepository y operaciones personalizadas.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Obtiene le usuario por su nombre.
     *
     * @param username Es el nombre del usuario.
     * @return Retorna un objeto que puede o encontrarse en la bsae de datos.
     * @see Optional
     */
    Optional<User> findByUsername(String username);
}
