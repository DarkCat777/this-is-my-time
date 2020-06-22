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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 32, message = "{authority.name.size}")
    @NotBlank(message = "{authority.name.notBlank}")
    @Pattern(regexp = "[a-zA-Z]+", message = "{authority.name.pattern}")
    @Column(length = 32, nullable = false, unique = true)
    private String authority;
    @NotNull(message = "{authority.isEnable.notNull}")
    @Column(nullable = false)
    private Boolean isEnable;
    @ManyToMany
    @JoinTable
    private Set<User> users = new HashSet<>();
    @ManyToMany
    private Set<Resource> resources = new HashSet<>();

    public Authority(String authority, Boolean isEnable) {
        this.setAuthority(authority);
        this.setIsEnable(isEnable);
    }
}
