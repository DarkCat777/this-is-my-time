package edu.app.server.model;

import lombok.extern.log4j.Log4j2;
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


@Log4j2
@SpringBootTest
public class UserTest {
    @Autowired
    private Validator validator;

    @DisplayName("Validating username in User")
    @ParameterizedTest(name = "#{index} - Test validation username = {0}")
    @CsvFileSource(resources = "/model/user/username_test.csv", numLinesToSkip = 1)
    void validateUsername(String username) {
        User user = new User();
        user.setUsername(username);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            if (violation.getPropertyPath().toString().equals("username") && !violation.getMessage().isEmpty()) {
                fail("This email(" + username + ") is not correct");
            }
        }
    }

    @DisplayName("Validating password in User")
    @ParameterizedTest(name = "#{index} - Test validation password = {0}")
    @CsvFileSource(resources = "/model/user/password_test.csv", numLinesToSkip = 1)
    void validatePassword(String password) {
        User user = new User();
        user.setPassword(password);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            if (violation.getPropertyPath().toString().equals("password") && !violation.getMessage().isEmpty()) {
                fail("This password(" + password + ") is not correct");
            }
        }
    }

    @DisplayName("Validating isEnable in User")
    @ParameterizedTest(name = "#{index} - Test validation isEnable = {0}")
    @ValueSource(booleans = {true, false})
    void validateIsEnable(Boolean isEnable) {
        User user = new User();
        user.setIsEnable(isEnable);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isEnable") && !violation.getMessage().isEmpty()) {
                fail("This isEnable(" + isEnable + ") is not correct");
            }
        }
    }
}
