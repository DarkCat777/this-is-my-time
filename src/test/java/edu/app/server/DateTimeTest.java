package edu.app.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class DateTimeTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Probando la periodicidad parte logica.");
    }

    long dairy = 1;
    long weekly = 7;
    long personalized = 4;
    LocalDate testLocalDate;

    @BeforeEach
    void beforeEach() {
        testLocalDate = LocalDate.of(2020, 1, 31);
        log.info(testLocalDate.toString());
    }

    @DisplayName("Probando logica sobre la periocidad diaria.")
    @ParameterizedTest
    @CsvSource({"1,1,1"})
    public void dairyTest(long day, long month, long year) {
        LocalDate newLocalDateDay = testLocalDate.plusDays(day);
        LocalDate newLocalDateMonth = testLocalDate.plusMonths(month);
        LocalDate newLocalDateMonth2 = newLocalDateMonth.plusMonths(month);
        log.info(newLocalDateMonth2.toString());
        LocalDate newLocalDateYear = testLocalDate.plusYears(year);
        log.info(newLocalDateDay.toString());
        log.info(newLocalDateMonth.toString());
        log.info(newLocalDateYear.toString());
    }
}
