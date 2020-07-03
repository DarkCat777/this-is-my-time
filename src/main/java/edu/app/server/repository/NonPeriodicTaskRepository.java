package edu.app.server.repository;

import edu.app.server.model.NonPeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonPeriodicTaskRepository extends JpaRepository<NonPeriodicTask, Long> {
}
