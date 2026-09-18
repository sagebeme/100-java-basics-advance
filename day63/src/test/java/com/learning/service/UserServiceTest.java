package com.learning.service;

import com.learning.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void savingThenLookingUpByEmailFindsTheSameUser() {
        userService.saveUser(new User("Amina", "amina@example.com"));

        var found = userService.getUserByEmail("amina@example.com");

        assertTrue(found.isPresent());
        assertEquals("Amina", found.get().getName());
    }

    @Test
    void updatingChangesTheStoredNameAndEmail() {
        User saved = userService.saveUser(new User("Amina", "amina@example.com"));

        User updated = userService.updateUser(saved.getId(), "Amina K.", "amina.k@example.com");

        assertEquals("Amina K.", updated.getName());
        assertEquals("amina.k@example.com", updated.getEmail());
    }

    @Test
    void updatingAnUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(999999L, "X", "x@example.com"));
    }

    @Test
    void deletingRemovesTheUserFromGetAllUsers() {
        User saved = userService.saveUser(new User("Delete Me", "delete.me@example.com"));
        int before = userService.getAllUsers().size();

        userService.deleteUser(saved.getId());

        assertEquals(before - 1, userService.getAllUsers().size());
        assertTrue(userService.getUserByEmail("delete.me@example.com").isEmpty());
    }
}
