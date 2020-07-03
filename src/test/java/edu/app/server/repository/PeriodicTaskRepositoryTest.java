package edu.app.server.repository;

import edu.app.server.model.PeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class PeriodicTaskRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PeriodicTaskRepository periodicTaskRepository;

    @Test
    @DisplayName("Test Resource Repository 'save' operation")
    void testResourceRepositoryOperationSave() {
        User user = new User("erickdmh@outlook.es", "983352035edmh", true);
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setName("Lavarme los dientes");
        periodicTask.setDescription("Me lavare los dientes XD");
        periodicTask.setState(State.IN_PROCCESS);
        periodicTask.setUser(user);
        userRepository.save(user);
        periodicTaskRepository.save(periodicTask);
        assertNotNull("No se guardo.", periodicTask.getId());
    }
}
