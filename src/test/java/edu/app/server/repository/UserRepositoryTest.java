package edu.app.server.repository;

import edu.app.server.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.junit.Assert.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

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
}
