package edu.app.server.repository;

import edu.app.server.model.Authority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.junit.Assert.*;

@DataJpaTest
public class AuthorityRepositoryTest {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Test
    @DisplayName("Test Authority Repository 'save' operation")
    void testAuthorityRepositoryOperationSave() {
        Authority authority = new Authority();
        authority.setAuthority("User");
        authority.setIsEnable(true);
        authorityRepository.save(authority);
        assertNotNull("No se guardo.", authority.getId());
    }

    @Test
    @DisplayName("Test Authority Repository 'update' operation")
    void testAuthorityRepositoryOperationUpdate() {
        Authority authority = new Authority();
        authority.setAuthority("Admin");
        authority.setIsEnable(true);
        this.authorityRepository.save(authority);
        Authority authorityRep = this.authorityRepository.getOne(authority.getId());
        assertEquals("No son iguales", authority, authorityRep);
    }

    @Test
    @DisplayName("Test Authority Repository 'delete' operation")
    void testAuthorityRepositoryOperationDelete() {
        Authority authority = new Authority();
        authority.setAuthority("System");
        authority.setIsEnable(true);
        this.authorityRepository.save(authority);
        this.authorityRepository.delete(authority);
        assertThrows(JpaObjectRetrievalFailureException.class, () -> this.authorityRepository.getOne(authority.getId()));
    }

    @Test
    @DisplayName("Test Authority Repository 'getAll' operation")
    void testAuthorityRepositoryOperationGetAll() {
        Authority authority = new Authority();
        authority.setAuthority("System");
        authority.setIsEnable(true);
        this.authorityRepository.save(authority);
        Authority authority1 = new Authority();
        authority1.setAuthority("Admin");
        authority1.setIsEnable(true);
        this.authorityRepository.save(authority1);
        assertEquals(2, this.authorityRepository.findAll().size());
    }
}
