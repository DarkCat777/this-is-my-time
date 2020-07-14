package edu.app.server.service;

import edu.app.server.model.Resource;
import edu.app.server.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResourceServiceTest {
    @MockBean
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceService resourceService;

    private Resource resource;

    @BeforeEach
    @DisplayName("Initialize Mock ResourceRepository")
    void beforeAll() {
        resource = new Resource("/newUser", HttpMethod.GET, true);
        Mockito.when(resourceRepository.findByResource("/newUser")).thenReturn(Optional.of(resource));
        Mockito.when(resourceRepository.findById(resource.getId())).thenReturn(Optional.of(resource));
    }

    @Test
    @DisplayName("Testing method getByResource()")
    void getByResourceNameTest() {
        Resource resource = resourceService.getByName("/newUser");
        assertEquals(resource, resourceRepository.findByResource("/newUser").get());
    }

    @Test
    @DisplayName("Testing method deleteResource()")
    void deleteResourceTest() {
        Resource resource = new Resource("/newUser", HttpMethod.GET, true);
        assertEquals(resourceService.deleteResource(resource), "Success delete resource");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        Resource resourceGet = resourceService.getById(resource.getId());
        assertEquals(resourceGet, resource);
    }
}
