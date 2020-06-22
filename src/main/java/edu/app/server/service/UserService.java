package edu.app.server.service;

import edu.app.server.model.User;
import edu.app.server.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User saveUser(@Validated User user) {
        log.info("Guardando usuario");
        log.info("Codificando el password");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

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

    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        log.info("Buscando usuario con el nombre usuario: " + username);
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("Recuperando todos los usuarios");
        return this.userRepository.findAll();
    }

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

    @Transactional(readOnly = true)
    public User getById(Long id) {
        log.info("Buscando usuario por su id: " + id);
        return this.userRepository.findById(id).orElseGet(null);
    }

}
