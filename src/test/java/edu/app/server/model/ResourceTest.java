package edu.app.server.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ResourceTest {
    @Autowired
    private Validator validator;

    @DisplayName("Validating name in Resource")
    @ParameterizedTest(name = "#{index} - Test validation name = {0}")
    @CsvFileSource(resources = "/model/resource/name_test.csv", numLinesToSkip = 1)
    void validateNameResource(String nameResource) {
        Resource resource = new Resource();
        resource.setResource(nameResource);
        Set<ConstraintViolation<Resource>> violations = validator.validate(resource);
        for (ConstraintViolation<Resource> violation : violations) {
            if (violation.getPropertyPath().toString().equals("resource") && !violation.getMessage().isEmpty()) {
                fail("This resource(" + nameResource + ") is not correct");
            }
        }
    }

    @DisplayName("Validating isEnable in Resource")
    @ParameterizedTest(name = "#{index} - Test validation isEnable = {0}")
    @ValueSource(booleans = {true, false})
    void validateIsEnable(Boolean isEnable) {
        Resource resource = new Resource();
        resource.setIsEnable(isEnable);
        Set<ConstraintViolation<Resource>> violations = validator.validate(resource);
        for (ConstraintViolation<Resource> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isEnable") && !violation.getMessage().isEmpty()) {
                fail("This isEnable(" + isEnable + ") is not correct");
            }
        }
    }
}
