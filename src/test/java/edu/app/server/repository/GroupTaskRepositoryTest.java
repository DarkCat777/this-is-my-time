package edu.app.server.repository;

import edu.app.server.model.GroupTask;
import edu.app.server.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.*;

@DataJpaTest
public class GroupTaskRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupTaskRepository groupTaskRepository;

    private User userTest = new User("erickdmh@outlook.es", "983352035edmh", true);
    private User userTest2 = new User("erickdmh@gmail.com", "983352035", true);

    @BeforeEach
    void beforeAllTest() {
        this.userRepository.save(userTest);
        this.userRepository.save(userTest2);
    }

    @Test
    @DisplayName("Test GroupTask Repository 'save' operation")
    void testGroupTaskRepositoryOperationSave() {
        GroupTask groupTask = new GroupTask();
        groupTask.setUser(userTest);
        groupTask.setName("Diario");
        this.groupTaskRepository.save(groupTask);
        assertNotNull("No se guardo.", groupTask.getId());
    }

    @Test
    @DisplayName("Test GroupTask Repository 'update' operation")
    void testGroupTaskRepositoryOperationUpdate() {
        GroupTask groupTask = new GroupTask();
        groupTask.setUser(userTest);
        groupTask.setName("Diario");
        this.groupTaskRepository.save(groupTask);
        groupTask.setUser(userTest2);
        GroupTask updatedGroupTask = this.groupTaskRepository.save(groupTask);
        assertEquals(userTest2, groupTask.getUser());
    }

    @Test
    @DisplayName("Test GroupTask Repository 'delete' operation")
    void testGroupTaskRepositoryOperationDelete() {
        GroupTask groupTask = new GroupTask();
        groupTask.setUser(userTest);
        groupTask.setName("Diario");
        this.groupTaskRepository.save(groupTask);
        this.groupTaskRepository.delete(groupTask);
        assertNull(this.groupTaskRepository.getById(groupTask.getId()).orElse(null));
    }
}
