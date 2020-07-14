package edu.app.server.repository;

import edu.app.server.model.GroupTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupTaskRepository extends JpaRepository<GroupTask, Long> {
    Optional<GroupTask> getById(Long id);

    Optional<GroupTask> findByName(String name);
}
