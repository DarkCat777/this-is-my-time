package edu.app.server.service;

import edu.app.server.model.Authority;
import edu.app.server.repository.AuthorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorityServiceTest {
    @MockBean
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthorityService authorityService;

    private Authority authority;

    @BeforeEach
    @DisplayName("Initialize Mock AuthorityRepository")
    void beforeAll() {
        authority = new Authority("Administrador", true);
        Mockito.when(authorityRepository.findByAuthority("Administrador")).thenReturn(Optional.of(authority));
        Mockito.when(authorityRepository.findById(authority.getId())).thenReturn(Optional.of(authority));
    }

    @Test
    @DisplayName("Testing method getByAuthority()")
    void getByAuthorityNameTest() {
        Authority authority = authorityService.getByName("Administrador");
        assertEquals(authority, authorityRepository.findByAuthority("Administrador").get());
    }

    @Test
    @DisplayName("Testing method deleteAuthority()")
    void deleteAuthorityTest() {
        Authority authority = new Authority("Administrador", true);
        assertEquals(authorityService.deleteAuthority(authority), "Success delete authority");
    }

    @Test
    @DisplayName("Testing method getById()")
    void getByIdTest() {
        Authority authorityGet = authorityService.getById(authority.getId());
        assertEquals(authorityGet, authority);
    }
}
