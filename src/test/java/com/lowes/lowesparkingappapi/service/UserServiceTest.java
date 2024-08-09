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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(appUserRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<AppUser> users = userService.getAllUsers();

        assertEquals(1, users.size());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));

        AppUser foundUser = userService.getUserById(1L);

        assertEquals(user, foundUser);
    }

    @Test
    void getUserById_shouldReturnNullIfNotExists() {
        when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

        AppUser foundUser = userService.getUserById(1L);

        assertNull(foundUser);
    }

    @Test
    void saveUser_shouldReturnSavedUser() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(appUserRepository.save(user)).thenReturn(user);

        AppUser savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        userService.deleteUser(1L);
        verify(appUserRepository).deleteById(1L);
    }
}
