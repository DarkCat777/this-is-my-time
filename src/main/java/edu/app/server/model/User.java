package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta es la clase User la cual es la representación de la tabla User en la base de datos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tasks"})
@Entity
public class User {
    /**
     * Este es el identificador de la tabla User el cual es generado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Es el nombre de usuario el cual es un correo electronico.
     * Este campo no puede ser vacio.
     * La longitud de caracteres de este campo no puede ser menor a 8 ni mayor a 24.
     * El atributo en la base de datos tiene la longitud de 24 caracteres maximo.
     */
    @NotBlank(message = "{user.username.notBlank}")
    @Email(message = "{user.username.email}")
    @Size(min = 8, max = 24, message = "{user.username.size}")
    @Column(unique = true, length = 24, nullable = false)
    private String username;
    /**
     * Es la contraseña del usuario la cual esta encriptada.
     * Su longitud minima es 8 y su longitud maxima es 64.
     * No puede estar vacia.
     * En la base de datos se guarda con la longitud de 64.
     */
    @Size(min = 8, max = 64, message = "{user.password.size}")
    @NotBlank(message = "{user.password.notBlank}")
    @Column(length = 64, nullable = false)
    private String password;
    /**
     * Es el estado del usuario (habilitado o inhabilitado).
     * Este atributo no puede ser nulo.
     */
    @NotNull(message = "{user.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    /**
     * Es un atributo exclusivo de la clase de java, que representa la relación de la clase con su autoridad respectiva.
     */
    @ManyToMany(mappedBy = "users")
    private Set<Authority> authorities = new HashSet<>();
    /**
     * Es un atributo exclusivo de la clase de java, que representa la relación de la clase con sus tareas respectivas.
     */
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    /**
     * Es el contructor de la clase User la cual solo tiene como parametros los atributos que no se inicializan automaticamente.
     *
     * @param username Es el nombre de usuario.
     * @param password Es la contraseña del usuario pero ya encriptada.
     * @param isEnable Es el estado del usuario.
     */
    public User(String username, String password, Boolean isEnable) {
        this.username = username;
        this.password = password;
        this.isEnable = isEnable;
    }
}
