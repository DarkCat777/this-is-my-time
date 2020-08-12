package edu.app.server.repository;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

@DataJpaTest
public class NonPeriodicTaskRepositoryTest {
    @Autowired
    private NonPeriodicTaskRepository nonPeriodicTaskRepository;
    @Autowired
    private UserRepository userRepository;
    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);

    @BeforeEach
    void beforeAllTest() {
        this.userRepository.save(userTest);
    }

    @Test
    @DisplayName("Test NonPeriodicTask Repository 'save' operation")
    void testNonPeriodicTaskRepositoryOperationSave() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setName("Lavarme los dientes");
        nonPeriodicTask.setDescription("");
        nonPeriodicTask.setState(State.IN_PROCESS);
        nonPeriodicTask.setUser(userTest);
        nonPeriodicTask.setInitTime(LocalTime.now());
        nonPeriodicTask.setInitDate(LocalDate.now());
        this.nonPeriodicTaskRepository.save(nonPeriodicTask);
        assertNotNull("No se guardo.", nonPeriodicTask.getId());
    }

    @Test
    @DisplayName("Test NonPeriodicTask Repository 'update' operation")
    void testNonPeriodicTaskRepositoryOperationUpdate() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setName("Brush my teeth");
        nonPeriodicTask.setDescription("");
        nonPeriodicTask.setState(State.IN_PROCESS);
        nonPeriodicTask.setUser(userTest);
        nonPeriodicTask.setInitTime(LocalTime.now());
        nonPeriodicTask.setInitDate(LocalDate.now());
        this.nonPeriodicTaskRepository.save(nonPeriodicTask);
        nonPeriodicTask.setDescription("I brush my tooth in the evening.");
        NonPeriodicTask nonPeriodicTaskUpdate = this.nonPeriodicTaskRepository.save(nonPeriodicTask);
        assertEquals("I brush my tooth in the evening.", nonPeriodicTaskUpdate.getDescription());
    }

    @Test
    @DisplayName("Test NonPeriodicTask Repository 'delete' operation")
    void testNonPeriodicTaskRepositoryOperationDelete() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setName("Brush my teeth");
        nonPeriodicTask.setDescription("");
        nonPeriodicTask.setState(State.IN_PROCESS);
        nonPeriodicTask.setUser(userTest);
        nonPeriodicTask.setInitTime(LocalTime.now());
        nonPeriodicTask.setInitDate(LocalDate.now());
        this.nonPeriodicTaskRepository.save(nonPeriodicTask);
        this.nonPeriodicTaskRepository.delete(nonPeriodicTask);
        assertNull(this.nonPeriodicTaskRepository.getById(nonPeriodicTask.getId()).orElse(null));
    }
}
