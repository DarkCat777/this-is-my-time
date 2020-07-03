package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class PeriodicTaskTest {
    @Autowired
    private Validator validator;

    @NullSource
    @ParameterizedTest
    @DisplayName("Validating name in Authority")
    void validateUserTask(User user) {
        Task task = new Task();
        task.setUser(user);
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("name") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask(" + user + ") is not correct");
            }
        }
    }

    @Test
    @DisplayName("Validating name in Authority")
    void validateUserTask2() {
        Task task = new Task();
        task.setUser(new User());
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("user") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask is not correct");
            }
        }
    }
}
