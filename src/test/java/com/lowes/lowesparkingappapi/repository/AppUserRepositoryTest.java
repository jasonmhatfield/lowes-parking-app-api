package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AppUserRepositoryTest {

    @Mock
    private AppUserRepository appUserRepository;

    private AppUser user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new AppUser();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);
        user.setRole("USER");
    }

    @Test
    public void testSaveUser() {
        user.setId(1L);
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser savedUser = appUserRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals("Test", savedUser.getFirstName());
    }

    @Test
    public void testFindAllUsers() {
        user.setId(1L);
        when(appUserRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<AppUser> users = appUserRepository.findAll();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());

        AppUser foundUser = users.get(0);

        assertNotNull(foundUser.getId());
        assertEquals("Test", foundUser.getFirstName());
        assertEquals("User", foundUser.getLastName());
        assertEquals("testuser@example.com", foundUser.getEmail());
        assertTrue(foundUser.isHasHandicapPlacard());
        assertFalse(foundUser.isHasEv());
        assertEquals("USER", foundUser.getRole());
    }

    @Test
    public void testFindUserById() {
        user.setId(1L);
        when(appUserRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<AppUser> foundUser = appUserRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(appUserRepository).deleteById(anyLong());

        appUserRepository.deleteById(user.getId());
        verify(appUserRepository, times(1)).deleteById(user.getId());
    }
}
