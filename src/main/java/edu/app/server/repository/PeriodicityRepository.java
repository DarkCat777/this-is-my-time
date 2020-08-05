package edu.app.server.repository;

import edu.app.server.model.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Es el repositorio de Periodicity el cual realiza las operaciones implementadas por JpaRepository.
 *
 * @author Erick David Carpio Hachiri
 * @see JpaRepository
 * @see Periodicity
 */
@Repository
public interface PeriodicityRepository extends JpaRepository<Periodicity, Long> {
}
