package edu.app.server.service;

import edu.app.server.model.PeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import edu.app.server.repository.PeriodicTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PeriodicTaskServiceTest {
    @MockBean
    private PeriodicTaskRepository periodicTaskRepository;
    @Autowired
    private PeriodicTaskService periodicTaskService;

    private PeriodicTask periodicTask;
    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);

    @BeforeEach
    @DisplayName("Initialize Mock PeriodicTaskRepository")
    void beforeAll() {
        periodicTask = new PeriodicTask("Diario", "Esta es la decripcion de la tarea.", State.IN_PROCESS, userTest);
        Mockito.when(periodicTaskRepository.findByName("Diario")).thenReturn(Optional.of(periodicTask));
        Mockito.when(periodicTaskRepository.findById(periodicTask.getId())).thenReturn(Optional.of(periodicTask));
    }

    @Test
    @DisplayName("Testing method getByPeriodicTask()")
    void getByPeriodicTaskNameTest() {
        PeriodicTask periodicTask = periodicTaskService.getByName("Diario");
        assertEquals(periodicTask, periodicTaskRepository.findByName("Diario").get());
    }

    @Test
    @DisplayName("Testing method deletePeriodicTask()")
    void deletePeriodicTaskTest() {
        PeriodicTask periodicTask = new PeriodicTask("Diario", "Esta es la decripcion de la tarea.", State.IN_PROCESS, userTest);
        assertEquals(periodicTaskService.deletePeriodicTask(periodicTask), "Success delete periodicTask");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        PeriodicTask periodicTaskGet = periodicTaskService.getById(periodicTask.getId());
        assertEquals(periodicTaskGet, periodicTask);
    }
}
