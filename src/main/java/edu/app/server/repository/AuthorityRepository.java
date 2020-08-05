package edu.app.server.repository;

import edu.app.server.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Es el repositorio que se encarga de realizar las operaciones con la base de datos mediante JPA, ademas trae mas metodos con extender la interface JpaRepository.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see Authority
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    /**
     * Busca el por el nombre de la autoridad.
     *
     * @param authority Es el nombre de autoridad.
     * @return Retorna un  Optional de tipo Authority.
     * @see Optional
     */
    Optional<Authority> findByAuthority(String authority);
}
