package edu.app.server.repository;

import edu.app.server.model.PeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodicTaskRepository extends JpaRepository<PeriodicTask, Long> {
    Optional<PeriodicTask> getById(Long id);
}
