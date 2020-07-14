package edu.app.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
     * Es el nombre del grupo de tareas.
     */
    @NotBlank(message = "{groupTask.name.notBlank}")
    @Pattern(regexp = "[a-zA-Z_ ]+", message = "{groupTask.name.pattern}")
    @Column(nullable = false, length = 64)
    private String name;
    /**
     * Es el usuario al cual pertenecen.
     */
    @ManyToOne
    @NotNull(message = "{groupTask.user.notNull}")
    private User user;
    /**
     * Son las tareas asociadas.
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    public GroupTask(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
