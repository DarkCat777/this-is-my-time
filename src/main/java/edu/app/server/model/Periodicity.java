package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Periodicity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FutureOrPresent
    private LocalDate initDate;
    /**
     * EL tiempo se hace las validaciones no sobre el objeto sino por las fecha
     * por ejemplo:
     * -> Futuro
     * -> Presente
     * -> Pasado
     * -> O combinaciones de estos. (Que no sean incompatibles.)
     */
    private LocalTime initTime;

}
