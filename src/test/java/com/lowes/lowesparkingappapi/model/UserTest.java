package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userModel_shouldWorkCorrectly() {
        // Test AllArgsConstructor
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertFalse(user.isHasHandicapPlacard());
        assertFalse(user.isHasEv());
        assertEquals("user", user.getRole());

        // Test NoArgsConstructor and Setters
        AppUser user2 = new AppUser();
        user2.setId(2L);
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setHasHandicapPlacard(true);
        user2.setHasEv(true);
        user2.setRole("admin");

        assertEquals(2L, user2.getId());
        assertEquals("Jane", user2.getFirstName());
        assertEquals("Smith", user2.getLastName());
        assertEquals("jane.smith@example.com", user2.getEmail());
        assertTrue(user2.isHasHandicapPlacard());
        assertTrue(user2.isHasEv());
        assertEquals("admin", user2.getRole());

        // Test Getters
        assertEquals(2L, user2.getId());
        assertEquals("Jane", user2.getFirstName());
        assertEquals("Smith", user2.getLastName());
        assertEquals("jane.smith@example.com", user2.getEmail());
        assertTrue(user2.isHasHandicapPlacard());
        assertTrue(user2.isHasEv());
        assertEquals("admin", user2.getRole());
    }
}
