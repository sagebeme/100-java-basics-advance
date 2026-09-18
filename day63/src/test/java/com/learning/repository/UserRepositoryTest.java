package com.learning.repository;

import com.learning.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void savingAssignsAGeneratedId() {
        User saved = userRepository.save(new User("Amina", "amina@example.com"));
        assertNotNull(saved.getId());
    }

    @Test
    void findsAUserByItsExactEmail() {
        userRepository.save(new User("Amina", "amina@example.com"));

        Optional<User> found = userRepository.findByEmail("amina@example.com");

        assertTrue(found.isPresent());
        assertEquals("Amina", found.get().getName());
    }

    @Test
    void aMissingEmailReturnsAnEmptyOptional() {
        assertTrue(userRepository.findByEmail("nobody@example.com").isEmpty());
    }

    @Test
    void findsUsersByAPartialNameMatch() {
        userRepository.save(new User("Amina Wanjiru", "amina@example.com"));
        userRepository.save(new User("Amina Otieno", "amina.o@example.com"));
        userRepository.save(new User("Zawadi", "zawadi@example.com"));

        List<User> matches = userRepository.findByNameContaining("Amina");

        assertEquals(2, matches.size());
    }

    @Test
    void duplicateEmailsAreRejectedByTheUniqueConstraint() {
        userRepository.saveAndFlush(new User("Amina", "amina@example.com"));

        assertThrows(Exception.class, () ->
                userRepository.saveAndFlush(new User("Someone Else", "amina@example.com")));
    }
}
