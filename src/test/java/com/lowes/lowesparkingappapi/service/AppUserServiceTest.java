package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.AppUser;
import com.lowes.lowesparkingappapi.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

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
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser savedUser = appUserService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Test", savedUser.getFirstName());
    }

    @Test
    public void testFindAllUsers() {
        when(appUserRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<AppUser> users = appUserService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Test", users.get(0).getFirstName());
    }

    @Test
    public void testFindUserById() {
        when(appUserRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<AppUser> foundUser = appUserService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("Test", foundUser.get().getFirstName());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(appUserRepository).deleteById(anyLong());

        appUserService.deleteUser(1L);

        verify(appUserRepository, times(1)).deleteById(1L);
    }
}
