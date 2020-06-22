package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{activity.name.notBlank}")
    @Pattern(regexp = "[a-zA-Z]+", message = "{activity.name.pattern}")
    @Size(min = 8, max = 32, message = "{activity.name.size}")
    @Column(nullable = false, length = 32)
    private String name;
    @NotBlank(message = "{activity.description.notBlank}")
    @Size(min = 8, max = 128, message = "{activity.description.size}")
    @Column(nullable = false, length = 128)
    private String description;
    @NotNull(message = "{activity.state.notNull}")
    @Enumerated(EnumType.STRING)
    private State state;

    public Task(String name, String description, State state) {
        this.setName(name);
        this.setDescription(description);
        this.setState(state);
    }
}
