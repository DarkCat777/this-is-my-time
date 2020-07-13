package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TaskTest {
    @Autowired
    private Validator validator;

    @ParameterizedTest(name = "#{index} - Test validation name = {0}")
    @DisplayName("Validating name in Task")
    @CsvFileSource(resources = "/model/task/task_name.csv", numLinesToSkip = 1)
    void validateNameTask(String namePeriodicTask) {
        Task task = new Task();
        task.setName(namePeriodicTask);
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("name") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask(" + namePeriodicTask + ") is not correct");
            }
        }
    }

    @NullAndEmptySource
    @ParameterizedTest(name = "#{index} - Test validation description = {0}")
    @DisplayName("Validating description in Task")
    @CsvFileSource(resources = "/model/task/task_description.csv", numLinesToSkip = 1)
    void validateDescriptionTask(String description) {
        Task task = new Task();
        task.setDescription(description);
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("description") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask(" + description + ") is not correct");
            }
        }
    }

    @ParameterizedTest(name = "#{index} - Test validation state = {0}")
    @DisplayName("Validating name in Authority")
    @EnumSource(
            value = State.class,
            names = {"IN_PROCESS", "PENDING", "SUSPENDED", "TERMINATED"})
    void validateStateTask(State state) {
        Task task = new Task();
        task.setState(state);
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("state") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask(" + state + ") is not correct");
            }
        }
    }

    @NullSource
    @ParameterizedTest
    @DisplayName("Validating null user in PeriodicTask")
    void validateTaskUserNull(User user) {
        Task task = new Task();
        task.setUser(user);
        boolean noPassTest = true;
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        for (ConstraintViolation<Task> violation : violations) {
            if (violation.getPropertyPath().toString().equals("name") && !violation.getMessage().isEmpty()) {
                assertNotNull(violation);
                noPassTest = false;
            }
        }
        if (noPassTest) {
            fail("This periodicTask(" + user + ") is not correct");
        }
    }

    @Test
    @DisplayName("Validating empty user in PeriodicTask")
    void validateTaskUserEmpty() {
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
