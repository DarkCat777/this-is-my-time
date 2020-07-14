package edu.app.server.service;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import edu.app.server.repository.NonPeriodicTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NonPeriodicTaskServiceTest {
    @MockBean
    private NonPeriodicTaskRepository nonPeriodicTaskRepository;
    @Autowired
    private NonPeriodicTaskService nonPeriodicTaskService;

    private NonPeriodicTask nonPeriodicTask;
    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);

    @BeforeEach
    @DisplayName("Initialize Mock NonPeriodicTaskRepository")
    void beforeAll() {
        nonPeriodicTask = new NonPeriodicTask("Diario", "Esta es la decripcion de la tarea.", State.IN_PROCESS, userTest, LocalDate.now(), LocalTime.now());
        Mockito.when(nonPeriodicTaskRepository.findByName("Diario")).thenReturn(Optional.of(nonPeriodicTask));
        Mockito.when(nonPeriodicTaskRepository.findById(nonPeriodicTask.getId())).thenReturn(Optional.of(nonPeriodicTask));
    }

    @Test
    @DisplayName("Testing method getByPeriodicTask()")
    void getByPeriodicTaskNameTest() {
        NonPeriodicTask nonPeriodicTask = nonPeriodicTaskService.getByName("Diario");
        assertEquals(nonPeriodicTask, nonPeriodicTaskRepository.findByName("Diario").get());
    }

    @Test
    @DisplayName("Testing method deletePeriodicTask()")
    void deletePeriodicTaskTest() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask("Diario", "Esta es la decripcion de la tarea.", State.IN_PROCESS, userTest, LocalDate.now(), LocalTime.now());
        assertEquals(nonPeriodicTaskService.deleteNonPeriodicTask(nonPeriodicTask), "Success delete nonPeriodicTask");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        NonPeriodicTask nonPeriodicTaskGet = nonPeriodicTaskService.getById(nonPeriodicTask.getId());
        assertEquals(nonPeriodicTaskGet, nonPeriodicTask);
    }

}
