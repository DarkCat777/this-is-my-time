package edu.app.server.repository;

import edu.app.server.model.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@DataJpaTest
public class ResourceRepositoryTest {
    @Autowired
    private ResourceRepository resourceRepository;

    @Test
    @DisplayName("Test Resource Repository 'save' operation")
    void testResourceRepositoryOperationSave() {
        Resource resource = new Resource();
        resource.setResource("/users");
        resource.setIsEnable(true);
        resourceRepository.save(resource);
        assertNotNull("No se guardo.", resource.getId());
    }

    @Test
    @DisplayName("Test Resource Repository 'update' operation")
    void testResourceRepositoryOperationUpdate() {
        Resource resource = new Resource();
        resource.setResource("/objects");
        resource.setIsEnable(true);
        this.resourceRepository.save(resource);
        Resource resourceRep = this.resourceRepository.getOne(resource.getId());
        assertEquals("No son iguales", resource, resourceRep);
    }

    @Test
    @DisplayName("Test Resource Repository 'delete' operation")
    void testResourceRepositoryOperationDelete() {
        Resource resource = new Resource();
        resource.setResource("/resource");
        resource.setIsEnable(true);
        this.resourceRepository.save(resource);
        this.resourceRepository.delete(resource);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            this.resourceRepository.getOne(resource.getId());
        });
    }

    @Test
    @DisplayName("Test Resource Repository 'getAll' operation")
    void testResourceRepositoryOperationGetAll() {
        Resource resource = new Resource();
        resource.setResource("/resource");
        resource.setIsEnable(true);
        this.resourceRepository.save(resource);
        Resource resource1 = new Resource();
        resource1.setResource("/complete");
        resource1.setIsEnable(true);
        this.resourceRepository.save(resource1);
        assertEquals(2, this.resourceRepository.findAll().size());
    }
}
