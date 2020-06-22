package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{theme.name.notBlank}")
    @Size(min = 2, max = 32, message = "{theme.name.size}")
    @Column(unique = true, nullable = false, length = 32)
    private String name;
    @NotNull(message = "{theme.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;

    public Theme(String name, Boolean isEnable) {
        this.setName(name);
        this.setIsEnable(isEnable);
    }
}
