package edu.app.server.service;

import edu.app.server.model.Authority;
import edu.app.server.model.Resource;
import edu.app.server.repository.AuthorityRepository;
import edu.app.server.repository.ResourceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Es el modulo de servicio que implementa las logicas de negocio o transformaciones a los datos de Resource.
 *
 * @author Erick David Carpio Hachiri
 * @see Resource
 */
@Log4j2
@Service
public class ResourceService {
    /**
     * Es el repositorio de Resource.
     *
     * @see ResourceRepository
     */
    private final ResourceRepository resourceRepository;

    /**
     * Contructor que injecta las dependencias de Spring container.
     *
     * @param resourceRepository Es el repositorio que sera injectado por Spring.
     */
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * Obtiene el recurso mediante su id, mediante una transacción de solo lectura.
     *
     * @param id Es el id del Resource.
     * @return Retorna el valor existe o no existe que es retornado por el repositorio.
     * @see ResourceRepository
     */
    @Transactional(readOnly = true)
    public Resource getById(Long id) {
        log.info("Buscando recurso por su id: " + id);
        return this.resourceRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas los recursos, mediante una transacción de solo lectura.
     *
     * @return Son todas los recursos realizadas por el repositorio.
     * @see ResourceRepository
     */
    @Transactional(readOnly = true)
    public List<Resource> getAllResources() {
        log.info("Obteniendo todos los recursos");
        return this.resourceRepository.findAll();
    }

    /**
     * Realiza la busqueda por nombre, mediante una transacción de solo lectura.
     *
     * @param name Es el nombre del Recurso.
     * @return Es la autoridad que se busco.
     * @see ResourceRepository
     */
    @Transactional(readOnly = true)
    public Resource getByName(String name) {
        log.info("Obteniendo recurso por su nombre: " + name);
        return this.resourceRepository.findByResource(name).orElse(null);
    }

    /**
     * Guarda el recurso, mediante una transacción de escritura.
     *
     * @param resource Es el recurso a guardar.
     * @return Es el recurso con el id asignado en la base de datos.
     * @see ResourceRepository
     */
    @Transactional
    public Resource saveResource(Resource resource) {
        log.info("Guardando recurso: " + resource.toString());
        return this.resourceRepository.save(resource);
    }

    /**
     * Actualiza el recurso autoridad, mediante una transacción de escritura.
     *
     * @param resource Es el recurso a actualizar.
     * @return Es el recurso con el los valores actualizados en la base de datos.
     * @see ResourceRepository
     */
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

    /**
     * Elimina el recurso, mediante una transacción de escritura.
     *
     * @param resource Es el recurso a eliminar.
     * @return Es una cadena con la confirnación.
     * @see ResourceRepository
     */
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
