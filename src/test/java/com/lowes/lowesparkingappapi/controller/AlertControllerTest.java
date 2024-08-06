package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.AlertDto;
import com.lowes.lowesparkingappapi.service.AlertService;
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
class AlertControllerTest {

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    @Test
    void testGetAlertsForUser() {
        UUID userId = UUID.randomUUID();
        List<AlertDto> mockAlerts = List.of(new AlertDto("Parking is limited for your category."));

        when(alertService.getAlertsForUser(userId)).thenReturn(mockAlerts);

        ResponseEntity<List<AlertDto>> response = alertController.getAlertsForUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAlerts, response.getBody());
        verify(alertService, times(1)).getAlertsForUser(userId);
    }
}
