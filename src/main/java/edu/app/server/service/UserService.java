package edu.app.server.service;

import edu.app.server.model.User;
import edu.app.server.repository.AuthorityRepository;
import edu.app.server.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones a los datos de User.
 *
 * @author Erick David Carpio Hachiri
 * @see User
 */

@Log4j2
@Service
public class UserService {
    /**
     * Es el repositorio de Authority.
     *
     * @see AuthorityRepository
     */
    private final UserRepository userRepository;
    /**
     * Es el encriptador de datos.
     *
     * @see BCryptPasswordEncoder
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param userRepository        Es el repositorio que sera injectado por Spring.
     * @param bCryptPasswordEncoder Es el encriptador de datos que sera injectado por Spring.
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Guarda el usuario, mediante una transacción de escritura.
     *
     * @param user Es el usuario a guardar.
     * @return Es el usuario con el id asignado en la base de datos.
     * @see UserRepository
     */
    @Transactional
    public User saveUser(@Validated User user) {
        log.info("Guardando usuario");
        log.info("Codificando el password");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    /**
     * Actualiza el usuario, mediante una transacción de escritura.
     *
     * @param user Es el usuario a actualizar.
     * @return Es el usuario con el los valores actualizados en la base de datos.
     * @see UserRepository
     */
    @Transactional
    public User updateUser(@Validated User user) {
        log.info("Obteniendo el usuario a actualizar.");
        User oldUser = this.userRepository.findById(user.getId()).orElse(null);
        if (oldUser != null) {
            log.info("Actualizando el usuario");
            oldUser.setUsername(user.getUsername());
            log.info("Codificando el password");
            oldUser.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            oldUser.setIsEnable(user.getIsEnable());
            oldUser.setAuthorities(user.getAuthorities());
            return this.userRepository.save(oldUser);
        } else {
            log.info("No se actualizo ningun el usuario.");
            return null;
        }
    }

    /**
     * Realiza la busqueda por nombre, mediante una transacción de solo lectura.
     *
     * @param username Es el nombre del usuario.
     * @return Es el usuario que se busco.
     * @see UserRepository
     */
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        log.info("Buscando usuario con el nombre usuario: " + username);
        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        } else {
            log.info("Charging authorities: " + user.getAuthorities());
            return user;
        }
    }

    /**
     * Obtiene todas los usuarios, mediante una transacción de solo lectura.
     *
     * @return Son todas los usuarios realizadas por el repositorio.
     * @see UserRepository
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("Recuperando todos los usuarios");
        return this.userRepository.findAll();
    }

    /**
     * Elimina el usuario, mediante una transacción de escritura.
     *
     * @param user Es el usuario a eliminar.
     * @return Es una cadena con la confirnación.
     * @see UserRepository
     */
    @Transactional
    public String deleteUser(User user) {
        Optional<User> userObj = this.userRepository.findById(user.getId());
        User userFind = userObj.orElseGet(null);
        if (userFind == null) {
            log.info("No se borro el usuario.");
            return "Unsuccessful delete user";
        } else {
            log.info("Borrando el usuario: " + userFind.toString());
            this.userRepository.delete(userFind);
            return "Success delete user";
        }
    }

    /**
     * Obtiene el usuario mediante su id, mediante una transacción de solo lectura.
     *
     * @param id Es el id del User.
     * @return Retorna el valor existe o no existe que es retornado por el repositorio.
     * @see UserRepository
     */
    @Transactional(readOnly = true)
    public User getById(Long id) {
        log.info("Buscando usuario por su id: " + id);
        return this.userRepository.findById(id).orElseGet(null);
    }

}
