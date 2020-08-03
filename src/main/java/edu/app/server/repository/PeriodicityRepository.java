package edu.app.server.repository;

import edu.app.server.model.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicityRepository extends JpaRepository<Periodicity, Long> {
}
