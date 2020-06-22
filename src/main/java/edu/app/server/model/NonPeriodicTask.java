package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class NonPeriodicTask extends Task {
    @NotNull(message = "{nonPeriodicTask.initDate.notNull}")
    private LocalDate initDate;
    @NotNull(message = "{nonPeriodicTask.initTime.notNull}")
    private LocalTime initTime;
}
