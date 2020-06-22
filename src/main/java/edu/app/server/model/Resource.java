package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{resource.name.notBlank}")
    @Size(min = 1, max = 64, message = "{resource.name.size}")
    @Pattern(regexp = "^/[a-zA-Z]*(/[a-zA-Z]+)*(/[*][*])?", message = "{resource.name.pattern}")
    @Column(length = 64, nullable = false)
    private String resource;
    @NotNull(message = "{resource.httpMethod.notNull}")
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;
    @NotNull(message = "{resource.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    @ManyToMany
    @JoinTable
    private Set<Authority> authorities = new HashSet<>();

    public Resource(String resource, Boolean isEnable) {
        this.setResource(resource);
        this.setIsEnable(isEnable);
    }
}
