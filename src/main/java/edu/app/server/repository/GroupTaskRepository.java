package edu.app.server.repository;

import edu.app.server.model.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {
}
