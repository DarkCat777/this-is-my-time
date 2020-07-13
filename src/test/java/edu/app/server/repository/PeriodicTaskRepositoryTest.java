package edu.app.server.repository;

import edu.app.server.model.PeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.*;

@DataJpaTest
public class PeriodicTaskRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PeriodicTaskRepository periodicTaskRepository;

    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);

    @BeforeEach
    void beforeAllTest() {
        this.userRepository.save(userTest);
    }

    @Test
    @DisplayName("Test PeriodicTask Repository 'save' operation")
    void testPeriodicTaskRepositoryOperationSave() {
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setName("Lavarme los dientes");
        periodicTask.setDescription("");
        periodicTask.setState(State.IN_PROCESS);
        periodicTask.setUser(userTest);
        this.periodicTaskRepository.save(periodicTask);
        assertNotNull("No se guardo.", periodicTask.getId());
    }

    @Test
    @DisplayName("Test PeriodicTask Repository 'update' operation")
    void testPeriodicTaskRepositoryOperationUpdate() {
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setName("Brush my teeth");
        periodicTask.setDescription("");
        periodicTask.setState(State.IN_PROCESS);
        periodicTask.setUser(userTest);
        this.periodicTaskRepository.save(periodicTask);
        periodicTask.setDescription("I brush my tooth in the evening.");
        PeriodicTask periodicTaskUpdate = this.periodicTaskRepository.save(periodicTask);
        assertEquals("I brush my tooth in the evening.", periodicTaskUpdate.getDescription());
    }

    @Test
    @DisplayName("Test PeriodicTask Repository 'delete' operation")
    void testPeriodicTaskRepositoryOperationDelete() {
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setName("Brush my teeth");
        periodicTask.setDescription("");
        periodicTask.setState(State.IN_PROCESS);
        periodicTask.setUser(userTest);
        this.periodicTaskRepository.save(periodicTask);
        this.periodicTaskRepository.delete(periodicTask);
        assertNull(this.periodicTaskRepository.getById(periodicTask.getId()).orElse(null));
    }
}
