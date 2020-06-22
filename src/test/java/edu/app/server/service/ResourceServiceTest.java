package edu.app.server.service;

import edu.app.server.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ResourceServiceTest {
    @MockBean
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceService resourceService;
}
