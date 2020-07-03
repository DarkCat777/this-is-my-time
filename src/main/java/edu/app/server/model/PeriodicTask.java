package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase hereda de Task.
 * Son las tareas que se repiten de manera constante.
 *
 * @see Task
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PeriodicTask extends Task {
    /**
     * Se almacenn en otra tabla porque pueden asignarse multiples dias a las tareas.
     * Ejemplo:
     * - Lunes :. Clases de Matemetica a las 12.
     * - Martes :.Clases de Matematica a las 4.
     * - Estas tareas son semanales.
     */
    @ManyToMany
    @JoinTable
    private Set<Periodicity> periodicities = new HashSet<>();
}
