package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PeriodicTaskTest {
    @Autowired
    private Validator validator;

    @Test
    @DisplayName("Test notNull periodicities in PeriodicTask")
    void getPeriodicityInPeriodicTask() {
        PeriodicTask periodicTask = new PeriodicTask();
        assertNotNull(periodicTask.getPeriodicities());
    }
}
