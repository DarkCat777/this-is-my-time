package edu.app.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Esta clase es de la cual heredan las clases NonPeriodicTask y PeriodicTask,
 */

@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Task {
    /**
     * Es el id de Task, NonPeriodicTask y PeriodicTask.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    /**
     * Es el nombre de la tarea.
     */
    @NotBlank(message = "{task.name.notBlank}")
    @Pattern(regexp = "[a-zA-Z_ ]+", message = "{task.name.pattern}")
    @Size(min = 8, max = 32, message = "{task.name.size}")
    @Column(nullable = false, length = 32)
    protected String name;
    /**
     * Es la descripción de la tarea.
     */
    @NotBlank(message = "{task.description.notBlank}")
    @Size(min = 8, max = 128, message = "{task.description.size}")
    @Column(nullable = false, length = 128)
    protected String description;
    /**
     * Es el estado de la tarea.
     */
    @NotNull(message = "{task.state.notNull}")
    @Enumerated(EnumType.STRING)
    protected State state;

    /**
     * Es el usuario que es propetario de la tarea.
     */
    @ManyToOne
    @NotNull(message = "{task.user.notNull}")
    protected User user;

    /**
     * Es el constructor de la tarea.
     *
     * @param name        Es el nombre de la tarea.
     * @param description Es la descripción de la tarea.
     * @param state       Es el estado de la tarea.
     */
    public Task(String name, String description, State state, User user) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.user = user;
    }
}
