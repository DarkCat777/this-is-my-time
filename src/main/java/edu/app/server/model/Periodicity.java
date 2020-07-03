package edu.app.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Define la periodicidad de las tareas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Periodicity {
    /**
     * Es el id de la periodicidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Es el dia en el cual debe notificarse la actividad.
     */
    @NotNull(message = "{periodicity.initDate.notNull}")
    @FutureOrPresent(message = "{periodicity.initDate.futureOrPresent}")
    private LocalDate initDate;
    /**
     * EL tiempo se hace las validaciones no sobre el objeto sino por las fecha
     * por ejemplo:
     * -> Futuro
     * -> Presente
     * -> Pasado
     * -> O combinaciones de estos. (Que no sean incompatibles.)
     */
    @NotNull(message = "{periodicity.initTime.notNull}")
    private LocalTime initTime;
    /*
     * Se debe definir las personalización si sera diaria, mensual, semanal, anual.
     */
    @NotNull(message = "{periodicity.periodicityType.notNull}")
    @Enumerated(EnumType.STRING)
    private PeriodicityType periodicityType;
    /**
     * Es la cantidad de tiempo basado en el PeriodicityType.
     *
     * @see PeriodicityType
     */
    @Min(value = 1, message = "{periodicity.quantityOfPeriodicityType.min}")
    @NotNull(message = "{periodicity.quantityOfPeriodicityType.notNull}")
    private Integer quantityOfPeriodicityType;

    public Periodicity(LocalDate initDate, LocalTime initTime, PeriodicityType periodicityType, Integer quantityOfPeriodicityType) {
        this.initDate = initDate;
        this.initTime = initTime;
        this.periodicityType = periodicityType;
        this.quantityOfPeriodicityType = quantityOfPeriodicityType;
    }
}
