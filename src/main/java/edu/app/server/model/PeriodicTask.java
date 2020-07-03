package edu.app.server.model;

import lombok.*;

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
@ToString(callSuper = true)
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

    public PeriodicTask(String name, String description, State state, User user) {
        super(name, description, state, user);
    }
}
