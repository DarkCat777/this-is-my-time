package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TaskTest {
    @Autowired
    private Validator validator;

    @NullAndEmptySource
    @ParameterizedTest(name = "#{index} - Test validation name = {0}")
    @DisplayName("Validating name in Authority")
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

}
