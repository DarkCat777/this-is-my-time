package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class NonPeriodicTaskTest {
    @Autowired
    private Validator validator;

    @Test
    @DisplayName("Validating date.")
    void testInitDateNonPeriodicTask() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setInitDate(LocalDate.now());
        Set<ConstraintViolation<NonPeriodicTask>> violations = validator.validate(nonPeriodicTask);
        for (ConstraintViolation<NonPeriodicTask> violation : violations) {
            if (violation.getPropertyPath().toString().equals("initDate") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask is not correct");
            }
        }
    }

    @Test
    @DisplayName("Validating time.")
    void testInitTimeNonPeriodicTask() {
        NonPeriodicTask nonPeriodicTask = new NonPeriodicTask();
        nonPeriodicTask.setInitTime(LocalTime.now());
        Set<ConstraintViolation<NonPeriodicTask>> violations = validator.validate(nonPeriodicTask);
        for (ConstraintViolation<NonPeriodicTask> violation : violations) {
            if (violation.getPropertyPath().toString().equals("initTime") && !violation.getMessage().isEmpty()) {
                fail("This periodicTask is not correct");
            }
        }
    }
}
