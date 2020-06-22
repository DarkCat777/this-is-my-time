package edu.app.server.service;

import edu.app.server.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AuthorityServiceTest {
    @MockBean
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthorityService authorityService;
}
