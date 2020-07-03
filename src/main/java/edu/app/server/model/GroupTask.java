package edu.app.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Es la clase que define los grupos de las tareas y las asociaciones con los usuarios.
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupTask {
    /**
     * Es el identificador del grupo de tareas.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Es el usuario al cual pertenecen.
     */
    @ManyToOne
    @NotNull(message = "{groupTask.user.notNull}")
    private User user;
    /**
     * Son las tareas asociadas.
     */
    @OneToMany
    private Set<Task> tasks = new HashSet<>();

    public GroupTask(User user) {
        this.user = user;
    }
}
