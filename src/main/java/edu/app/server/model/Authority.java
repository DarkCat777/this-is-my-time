package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * La clase Authority sirve para dar roles a los usuarios y a que recursos pueden acceder.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    /**
     * Es el identificador de la tabla Authority en la base de datos el cual es generado de manera automatica.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Es el nombre de la Authority.
     * Este nombre esta restringido a una longitud de 4 a 32 caracteres.
     * Los caracteres que contiene deben ser estrictamente de la a-z y A-Z.
     * Este atributo tiene un tamaño de 32 en la base de datos.
     */
    @Size(min = 4, max = 32, message = "{authority.name.size}")
    @NotBlank(message = "{authority.name.notBlank}")
    @Pattern(regexp = "[a-zA-Z]+", message = "{authority.name.pattern}")
    @Column(length = 32, nullable = false, unique = true)
    private String authority;
    /**
     * Este atributo se encarga de definir si la autoridad esta habilitada o no.
     * Este atributo esta restringido a atributos no nulos.
     */
    @NotNull(message = "{authority.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    /**
     * Este atributo es exclusivo de esta clase java para manejar las relaciones que esta tabla tiene con la tabla de usuarios.
     */
    @ManyToMany
    @JoinTable
    private Set<User> users = new HashSet<>();
    /**
     * Este atributo es exclusivo de esta clase java para manejar la relacion que tiene con la tabla recursos.
     */
    @ManyToMany
    private Set<Resource> resources = new HashSet<>();

    /**
     * Este es el constructor de la clase con los atributos inicializables.
     *
     * @param authority Es el nombre de la autoridad.
     * @param isEnable  Es el estado de la autoridad.
     */
    public Authority(String authority, Boolean isEnable) {
        this.authority = authority;
        this.isEnable = isEnable;
    }
}
