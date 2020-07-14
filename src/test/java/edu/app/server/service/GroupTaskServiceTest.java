package edu.app.server.service;

import edu.app.server.model.GroupTask;
import edu.app.server.model.User;
import edu.app.server.repository.GroupTaskRepository;
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
public class GroupTaskServiceTest {
    @MockBean
    private GroupTaskRepository groupTaskRepository;
    @Autowired
    private GroupTaskService groupTaskService;

    private GroupTask groupTask;
    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);

    @BeforeEach
    @DisplayName("Initialize Mock GroupTaskRepository")
    void beforeAll() {
        groupTask = new GroupTask("Diario", userTest);
        Mockito.when(groupTaskRepository.findByName("Diario")).thenReturn(Optional.of(groupTask));
        Mockito.when(groupTaskRepository.findById(groupTask.getId())).thenReturn(Optional.of(groupTask));
    }

    @Test
    @DisplayName("Testing method getByGroupTask()")
    void getByGroupTaskNameTest() {
        GroupTask groupTask = groupTaskService.getByName("Diario");
        assertEquals(groupTask, groupTaskRepository.findByName("Diario").get());
    }

    @Test
    @DisplayName("Testing method deleteGroupTask()")
    void deleteGroupTaskTest() {
        GroupTask groupTask = new GroupTask("Diario", userTest);
        assertEquals(groupTaskService.deleteGroupTask(groupTask), "Success delete groupTask");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        GroupTask groupTaskGet = groupTaskService.getById(groupTask.getId());
        assertEquals(groupTaskGet, groupTask);
    }

}
