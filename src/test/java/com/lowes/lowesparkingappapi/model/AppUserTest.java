package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    @Test
    public void testNoArgsConstructor() {
        AppUser user = new AppUser();
        assertNotNull(user);
    }

    @Test
    public void testAllArgsConstructor() {
        AppUser user = new AppUser(1L, "Test", "User", "testuser@example.com", true, false, "USER");
        assertEquals(1L, user.getId());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("testuser@example.com", user.getEmail());
        assertTrue(user.isHasHandicapPlacard());
        assertFalse(user.isHasEv());
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);
        user.setRole("USER");

        assertEquals(1L, user.getId());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("testuser@example.com", user.getEmail());
        assertTrue(user.isHasHandicapPlacard());
        assertFalse(user.isHasEv());
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testSettersWithDifferentValues() {
        AppUser user = new AppUser();
        user.setId(2L);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("janedoe@example.com");
        user.setHasHandicapPlacard(false);
        user.setHasEv(true);
        user.setRole("ADMIN");

        assertEquals(2L, user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("janedoe@example.com", user.getEmail());
        assertFalse(user.isHasHandicapPlacard());
        assertTrue(user.isHasEv());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void testAllPossibleBranchCombinations() {
        // Test with all booleans set to true
        AppUser user1 = new AppUser(1L, "First", "Last", "email1@example.com", true, true, "USER");
        assertTrue(user1.isHasHandicapPlacard());
        assertTrue(user1.isHasEv());

        // Test with all booleans set to false
        AppUser user2 = new AppUser(2L, "First", "Last", "email2@example.com", false, false, "ADMIN");
        assertFalse(user2.isHasHandicapPlacard());
        assertFalse(user2.isHasEv());

        // Test mixed booleans
        AppUser user3 = new AppUser(3L, "First", "Last", "email3@example.com", true, false, "MODERATOR");
        assertTrue(user3.isHasHandicapPlacard());
        assertFalse(user3.isHasEv());

        AppUser user4 = new AppUser(4L, "First", "Last", "email4@example.com", false, true, "GUEST");
        assertFalse(user4.isHasHandicapPlacard());
        assertTrue(user4.isHasEv());
    }

    @Test
    public void testNullValues() {
        AppUser user = new AppUser();
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setRole(null);

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }
}
