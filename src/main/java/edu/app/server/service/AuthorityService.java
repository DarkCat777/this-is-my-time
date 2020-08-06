package edu.app.server.service;

import edu.app.server.model.Authority;
import edu.app.server.repository.AuthorityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones a los datos de Authority.
 *
 * @author Erick David Carpio Hachiri
 * @see Authority
 */
@Log4j2
@Service
public class AuthorityService {
    /**
     * Es el repositorio de Authority.
     *
     * @see AuthorityRepository
     */
    private final AuthorityRepository authorityRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param authorityRepository Es el repositorio que sera injectado por Spring.
     */
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    /**
     * Obtiene la autoridad mediante su id, mediante una transacción de solo lectura.
     *
     * @param id Es el id del Authority.
     * @return Retorna el valor existe o no existe que es retornado por el repositorio.
     * @see AuthorityRepository
     */
    @Transactional(readOnly = true)
    public Authority getById(Long id) {
        log.info("Buscando autoridad por su id: " + id);
        return this.authorityRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las autoridades, mediante una transacción de solo lectura.
     *
     * @return Son todas las autoridades realizadas por el repositorio.
     */
    @Transactional(readOnly = true)
    public List<Authority> getAllAuthorities() {
        log.info("Obteniendo todos las autoridades.");
        return this.authorityRepository.findAll();
    }

    /**
     * Guarda la autoridad, mediante una transacción de escritura.
     *
     * @param authority Es la autoridad a guardar.
     * @return Es la autoridad con el id asignado en la base de datos.
     */
    @Transactional
    public Authority saveAuthority(Authority authority) {
        log.info("Guardando autoridad" + authority.toString());
        return this.authorityRepository.save(authority);
    }

    /**
     * Actualiza la autoridad, mediante una transacción de escritura.
     *
     * @param authority Es la autoridad a actualizar.
     * @return Es la autoridad con el los valores actualizados en la base de datos.
     */
    @Transactional
    public Authority updateAuthority(Authority authority) {
        log.info("Buscando autoridad.");
        Authority oldAuthority = this.authorityRepository.findById(authority.getId()).orElse(null);
        if (oldAuthority != null) {
            log.info("Actualizando autoridad: " + authority.toString());
            oldAuthority.setAuthority(authority.getAuthority());
            oldAuthority.setIsEnable(authority.getIsEnable());
            oldAuthority.setUsers(authority.getUsers());
            oldAuthority.setResources(authority.getResources());
            return this.authorityRepository.save(oldAuthority);
        } else {
            log.info("No se encontro una autoridad previa, no se guardan los datos.");
            return null;
        }
    }

    /**
     * Elimina la autoridad, mediante una transacción de escritura.
     *
     * @param authority Es la autoridad a eliminar.
     * @return Es una cadena con la confirnación.
     */
    @Transactional
    public String deleteAuthority(Authority authority) {
        Authority oldAuthority = this.authorityRepository.findById(authority.getId()).orElse(null);
        if (oldAuthority == null) {
            log.info("No se borro el autoridad.");
            return "Unsuccessful delete authority";
        } else {
            log.info("Borrando la autoridad: " + oldAuthority.toString());
            this.authorityRepository.delete(oldAuthority);
            return "Success delete authority";
        }
    }

    /**
     * Realiza la busqueda por nombre, mediante una transacción de solo lectura.
     *
     * @param name Es el nombre de la autoridad.
     * @return Es la autoridad que se busco.
     */
    @Transactional(readOnly = true)
    public Authority getByName(String name) {
        log.info("Buscando autoridad por su nombre: " + name);
        return this.authorityRepository.findByAuthority(name).orElse(null);
    }
}
