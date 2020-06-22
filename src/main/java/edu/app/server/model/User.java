package edu.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{user.username.notBlank}")
    @Email(message = "{user.username.email}")
    @Size(min = 8, max = 24, message = "{user.username.size}")
    @Column(unique = true, length = 24, nullable = false)
    private String username;
    @Size(min = 8, max = 64, message = "{user.password.size}")
    @NotBlank(message = "{user.password.notBlank}")
    @Column(unique = false, length = 64, nullable = false)
    private String password;
    @NotNull(message = "{user.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    @ManyToMany(mappedBy = "users")
    private Set<Authority> authorities = new HashSet<>();

    public User(String username, String password, Boolean enable) {
        this.setUsername(username);
        this.setPassword(password);
        this.setIsEnable(enable);
    }
}
