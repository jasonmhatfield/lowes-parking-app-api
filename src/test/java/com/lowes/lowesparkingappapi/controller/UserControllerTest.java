package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
import com.lowes.lowesparkingappapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetAllUsers() {
        List<UserDto> mockUsers = List.of(UserDto.builder().build());
        when(userService.getAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        UUID userId = UUID.randomUUID();
        UserDto mockUser = UserDto.builder().build();
        when(userService.getUserById(userId)).thenReturn(mockUser);

        ResponseEntity<UserDto> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found with id: " + userId));

        ResponseEntity<UserDto> response = userController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testCreateUser() {
        UserDto newUser = UserDto.builder().build();
        UserDto mockUser = UserDto.builder().build();
        when(userService.createUser(newUser)).thenReturn(mockUser);

        ResponseEntity<UserDto> response = userController.createUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).createUser(newUser);
    }

    @Test
    void testUpdateUser() {
        UUID userId = UUID.randomUUID();
        UserDto updatedUser = UserDto.builder().build();
        UserDto mockUser = UserDto.builder().build();
        when(userService.updateUser(userId, updatedUser)).thenReturn(mockUser);

        ResponseEntity<UserDto> response = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void testUpdateUserNotFound() {
        UUID userId = UUID.randomUUID();
        UserDto updatedUser = UserDto.builder().build();
        when(userService.updateUser(userId, updatedUser)).thenThrow(new UserNotFoundException("User not found with id: " + userId));

        ResponseEntity<UserDto> response = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        UUID userId = UUID.randomUUID();
        doThrow(new UserNotFoundException("User not found with id: " + userId)).when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testRegisterUser() {
        UserDto newUser = UserDto.builder().build();
        UserDto mockUser = UserDto.builder().build();
        when(userService.createUser(newUser)).thenReturn(mockUser);

        ResponseEntity<UserDto> response = userController.registerUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
        verify(userService, times(1)).createUser(newUser);
    }
}
