package edu.app.server.service;

import edu.app.server.model.User;
import edu.app.server.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    @DisplayName("Initialize Mock UserRepository")
    void beforeAll() {
        User user = new User("erickdmh@outlook.es", "1234567", true);
        Mockito.when(userRepository.findByUsername("erickdmh@outlook.es")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Testing method getByUsername()")
    void getByUsernameTest() {
        User user = userService.getByUsername("erickdmh@outlook.es");
        assertEquals(user, userRepository.findByUsername("erickdmh@outlook.es").get());
    }

    @Test
    @DisplayName("Testing method deleteUser()")
    void deleteUserTest() {
        User user = new User("erickdmh@outlook.es", "1234567", true);
        assertEquals(userService.deleteUser(user), "Success delete user");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        Long id = 1L;
        User user = userService.getById(id);
        assertEquals(user, userRepository.findById(id).get());
    }
}
