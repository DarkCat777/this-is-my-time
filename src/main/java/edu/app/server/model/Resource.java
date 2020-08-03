package edu.app.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 * La clases Resource es la encargada de almacenar las URL disponibles y por cual metodo HTTP esta disponible.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    /**
     * Es el identificado de la tabla de Resource en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Es la url del recurso.
     * No puede estar vacio.
     * No puede tener una longitud mayor a 64.
     * Tiene la longitud de 64 en la base de datos.
     * Se verifica meditante la siguiente expresión regular ("^/[a-zA-Z]*(/[a-zA-Z]+)*(/[*][*])?").
     */
    @NotBlank(message = "{resource.name.notBlank}")
    @Size(min = 1, max = 64, message = "{resource.name.size}")
    @Pattern(regexp = "^/[a-zA-Z]*(/[a-zA-Z]+)*(/[*][*])?", message = "{resource.name.pattern}")
    @Column(length = 64, nullable = false)
    private String resource;
    /**
     * Es le metodo Http que se usa para acceder o usar este recurso.
     * No admite valores nulos.
     */
    @NotNull(message = "{resource.httpMethod.notNull}")
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;
    /**
     * Es el estado del recurso (habilitado o inhabilitado).
     * No admite valores nulos.
     */
    @NotNull(message = "{resource.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    /**
     * Es un atributo exclusivo de la clase java, porque representa la relación con la tabla Authority.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    /**
     * Este constructor inicializa los valores que no se inicializan de forma automatica.
     *
     * @param resource Es la url del recurso.
     * @param isEnable Es el estado del recurso.
     */
    public Resource(String resource, HttpMethod httpMethod, Boolean isEnable) {
        this.resource = resource;
        this.httpMethod = httpMethod;
        this.isEnable = isEnable;
    }
}
