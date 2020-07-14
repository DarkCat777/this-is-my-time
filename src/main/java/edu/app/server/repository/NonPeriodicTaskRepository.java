package edu.app.server.repository;

import edu.app.server.model.NonPeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NonPeriodicTaskRepository extends JpaRepository<NonPeriodicTask, Long> {
    Optional<NonPeriodicTask> getById(Long id);

    Optional<NonPeriodicTask> findByName(String name);
}
