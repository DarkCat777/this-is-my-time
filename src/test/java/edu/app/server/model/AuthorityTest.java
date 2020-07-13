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
public class AuthorityTest {
    @Autowired
    private Validator validator;

    @ParameterizedTest(name = "#{index} - Test validation name = {0}")
    @DisplayName("Validating name in Authority")
    @CsvFileSource(resources = "/model/authority/name_test.csv", numLinesToSkip = 1)
    void validateNameAuthority(String nameAuthority) {
        Authority authority = new Authority();
        authority.setAuthority(nameAuthority);
        Set<ConstraintViolation<Authority>> violations = validator.validate(authority);
        for (ConstraintViolation<Authority> violation : violations) {
            if (violation.getPropertyPath().toString().equals("authority") && !violation.getMessage().isEmpty()) {
                fail("This authority(" + nameAuthority + ") is not correct");
            }
        }
    }

    @DisplayName("Validating isEnable in Authority")
    @ParameterizedTest(name = "#{index} - Test validation isEnable = {0}")
    @ValueSource(booleans = {true, false})
    void validateIsEnable(Boolean isEnable) {
        Authority authority = new Authority();
        authority.setIsEnable(isEnable);
        Set<ConstraintViolation<Authority>> violations = validator.validate(authority);
        for (ConstraintViolation<Authority> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isEnable") && !violation.getMessage().isEmpty()) {
                fail("This isEnable(" + isEnable + ") is not correct");
            }
        }
    }
}
