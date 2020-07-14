package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class GroupTaskTest {
    @Autowired
    private Validator validator;

    @Test
    @DisplayName("Test in Group Task in attribute name.")
    void testNameInGroupTask() {
        GroupTask groupTask = new GroupTask();
        Set<ConstraintViolation<GroupTask>> violations = validator.validate(groupTask);
        boolean isViolationValidation = false;
        for (ConstraintViolation<GroupTask> violation : violations) {
            if (violation.getPropertyPath().toString().equals("name") && !violation.getMessage().isEmpty()) {
                isViolationValidation = true;
            }
        }
        if (!isViolationValidation) {
            fail("This group is not correct");
        }
    }

    @ParameterizedTest(name = "#{index} - Test validation name = {0}")
    @DisplayName("Validating name in GroupTask")
    @CsvFileSource(resources = "/model/groupTask/group_task_name.csv", numLinesToSkip = 1)
    void validateNameGroupTaskTest(String name) {
        GroupTask groupTask = new GroupTask();
        groupTask.setName(name);
        Set<ConstraintViolation<GroupTask>> violations = validator.validate(groupTask);
        for (ConstraintViolation<GroupTask> violation : violations) {
            if (violation.getPropertyPath().toString().equals("groupTask") && !violation.getMessage().isEmpty()) {
                fail("This groupTask(" + name + ") is not correct");
            }
        }
    }

    @Test
    @DisplayName("Test in Group Task 'User not null'.")
    void testUserInGroupTask() {
        GroupTask groupTask = new GroupTask();
        Set<ConstraintViolation<GroupTask>> violations = validator.validate(groupTask);
        boolean isViolationValidation = false;
        for (ConstraintViolation<GroupTask> violation : violations) {
            if (violation.getPropertyPath().toString().equals("user") && !violation.getMessage().isEmpty()) {
                isViolationValidation = true;
            }
        }
        if (!isViolationValidation) {
            fail("This group is not correct");
        }
    }

    @Test
    @DisplayName("Test in Group Task 'Tasks not null'.")
    void testTasksNotNullInGroupTask() {
        GroupTask groupTask = new GroupTask();
        assertNotNull(groupTask.getTasks());
    }
}
