package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.AlertDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AlertService alertService;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new User();
        user.setUserId(userId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setRole("USER");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);
    }

    @Test
    void testGetAlertsForUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<AlertDto> alerts = alertService.getAlertsForUser(userId);

        assertEquals(1, alerts.size());
        assertEquals("Parking is limited for your category.", alerts.get(0).getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAlertsForUserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            alertService.getAlertsForUser(userId);
        });

        String expectedMessage = "User not found with id: " + userId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepository, times(1)).findById(userId);
    }
}
