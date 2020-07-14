package edu.app.server.service;

import edu.app.server.model.Authority;
import edu.app.server.repository.AuthorityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    public Authority getById(Long id) {
        log.info("Buscando autoridad por su id: " + id);
        return this.authorityRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Authority> getAllAuthorities() {
        log.info("Obteniendo todos las autoridades.");
        return this.authorityRepository.findAll();
    }

    @Transactional
    public Authority saveAuthority(Authority authority) {
        log.info("Guardando autoridad" + authority.toString());
        return this.authorityRepository.save(authority);
    }

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

    @Transactional(readOnly = true)
    public Authority getByName(String name) {
        log.info("Buscando autoridad por su nombre: " + name);
        return this.authorityRepository.findByAuthority(name).orElse(null);
    }
}
