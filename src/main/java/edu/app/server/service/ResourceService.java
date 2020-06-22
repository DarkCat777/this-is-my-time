package edu.app.server.service;

import edu.app.server.model.Resource;
import edu.app.server.repository.ResourceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional(readOnly = true)
    public Resource getById(Long id) {
        log.info("Buscando recurso por su id: " + id);
        return this.resourceRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Resource> getAllResources() {
        log.info("Obteniendo todos los recursos");
        return this.resourceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Resource getByName(String name) {
        log.info("Obteniendo recurso por su nombre: " + name);
        return this.resourceRepository.findByResource(name).orElse(null);
    }

    @Transactional
    public Resource saveResource(Resource resource) {
        log.info("Guardando recurso: " + resource.toString());
        return this.resourceRepository.save(resource);
    }

    @Transactional
    public Resource updateResource(Resource resource) {
        Resource oldResource = this.resourceRepository.findById(resource.getId()).orElse(null);
        if (oldResource != null) {
            log.info("Actualizando recurso: " + resource.toString());
            oldResource.setResource(resource.getResource());
            oldResource.setIsEnable(resource.getIsEnable());
            oldResource.setAuthorities(resource.getAuthorities());
            return this.resourceRepository.save(oldResource);
        } else {
            log.info("No se encontro un recurso previo, no se guardan los datos.");
            return null;
        }
    }

    @Transactional
    public String deleteResource(Resource resource) {
        Resource oldResource = this.resourceRepository.findById(resource.getId()).orElse(null);
        if (oldResource == null) {
            log.info("No se borro el recurso.");
            return "Unsuccessful delete resource";
        } else {
            log.info("Borrando el recurso: " + oldResource.toString());
            this.resourceRepository.delete(oldResource);
            return "Success delete resource";
        }
    }
}
