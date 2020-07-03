package edu.app.server.repository;

import edu.app.server.model.NonPeriodicTask;
import edu.app.server.model.PeriodicTask;
import edu.app.server.model.State;
import edu.app.server.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PeriodicTaskRepository periodicTaskRepository;
    @Autowired
    private NonPeriodicTaskRepository nonPeriodicTaskRepository;

    @Test
    @DisplayName("Test User Repository 'save' operation")
    void testUserRepositoryOperationSave() {
        User user = new User();
        user.setUsername("erickdmh@outlook.es");
        user.setIsEnable(true);
        user.setPassword("983352035edmh");
        userRepository.save(user);
        assertNotNull("No se guardo.", user.getId());
    }

    @Test
    @DisplayName("Test User Repository 'update' operation")
    void testUserRepositoryOperationUpdate() {
        User user = new User();
        user.setUsername("erickdmh@outlook.es");
        user.setIsEnable(true);
        user.setPassword("983352035edmh");
        this.userRepository.save(user);
        User userRep = this.userRepository.getOne(user.getId());
        assertEquals("No son iguales", user, userRep);
    }

    @Test
    @DisplayName("Test User Repository 'delete' operation")
    void testUserRepositoryOperationDelete() {
        User user = new User();
        user.setUsername("erickdmh@outlook.es");
        user.setIsEnable(true);
        user.setPassword("983352035edmh");
        this.userRepository.save(user);
        this.userRepository.delete(user);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> this.userRepository.getOne(user.getId()));
    }

    @Test
    @DisplayName("Test User Repository 'getAll' operation")
    void testUserRepositoryOperationGetAll() {
        User user = new User();
        user.setUsername("erickdmh@outlook.es");
        user.setIsEnable(true);
        user.setPassword("983352035edmh");
        this.userRepository.save(user);
        User user1 = new User();
        user1.setUsername("erickdmh@hotmail.com");
        user1.setIsEnable(true);
        user1.setPassword("983352035");
        this.userRepository.save(user1);
        assertEquals(2, this.userRepository.findAll().size());
    }

    @Test
    void testUserWithTask() {
        User user = new User("erickdmh@outlook.es", "983352035edmh", true);
        userRepository.save(user);
        PeriodicTask periodicTask = new PeriodicTask("Hello World", "asjd asdjaskd", State.PENDING, user);
        periodicTaskRepository.save(periodicTask);
        System.out.println("periodicTask = " + periodicTask);
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask("Hello World", "asjd asdjaskd", State.PENDING, user, LocalDate.now(), LocalTime.now());
        nonPeriodicTaskRepository.save(nonPeriodicTask);
        System.out.println("nonPeriodicTask = " + nonPeriodicTask);
        user.getTasks().add(periodicTask);
        user.getTasks().add(nonPeriodicTask);
        userRepository.save(user);
    }
}
