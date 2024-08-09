package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.AppUser;
import com.lowes.lowesparkingappapi.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AppUserControllerTest {

    @Mock
    private AppUserService userService;

    @InjectMocks
    private AppUserController appUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        List<AppUser> users = appUserController.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getFirstName());
    }

    @Test
    void createUser_shouldReturnCreatedUser() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(userService.saveUser(user)).thenReturn(user);

        AppUser createdUser = appUserController.createUser(user);

        assertEquals(user, createdUser);
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() {
        AppUser user = new AppUser(1L, "John", "Doe", "john.doe@example.com", false, false, "user");
        when(userService.saveUser(user)).thenReturn(user);

        AppUser updatedUser = appUserController.updateUser(1L, user);

        assertEquals(user, updatedUser);
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        appUserController.deleteUser(1L);
    }
}
