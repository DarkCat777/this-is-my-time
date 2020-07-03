package edu.app.server.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Esta clase extiende la clase Task.
 * Define la tarea que no tiene un periocidicidad y se llevara a cabo en un solo dia.
 *
 * @see Task
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class NonPeriodicTask extends Task {
    /**
     * Es el dia en el cual inicia la tarea.
     */
    @NotNull(message = "{nonPeriodicTask.initDate.notNull}")
    @FutureOrPresent(message = "{nonPeriodicTask.initDate.futureOrPresent}")
    private LocalDate initDate;
    /**
     * Es el tiempo en el cual incia la tarea.
     */
    @NotNull(message = "{nonPeriodicTask.initTime.notNull}")
    private LocalTime initTime;

    public NonPeriodicTask(String name, String description, State state, LocalDate initDate, LocalTime initTime, User user) {
        super(name, description, state, user);
        this.initDate = initDate;
        this.initTime = initTime;
    }
}
