package edu.app.server.repository;

import edu.app.server.model.PeriodicTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicTaskRepository extends JpaRepository<PeriodicTask, Long> {

}
