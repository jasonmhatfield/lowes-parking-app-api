package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.service.ParkingSimulationService;
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
class ParkingSimulationControllerTest {

    @Mock
    private ParkingSimulationService parkingSimulationService;

    @InjectMocks
    private ParkingSimulationController parkingSimulationController;

    @Test
    void testSimulateParkingLoad() {
        List<ParkingSpaceDto> mockParkingSpaces = List.of(ParkingSpaceDto.builder().spaceId(UUID.randomUUID())
                .floorId(UUID.randomUUID()).spaceNumber("A1").isOccupied(false)
                .type(null) // Replace with actual ParkingSpaceType if needed
                .build());

        when(parkingSimulationService.simulateParkingLoad("Monday", 8)).thenReturn(mockParkingSpaces);

        ResponseEntity<List<ParkingSpaceDto>> response = parkingSimulationController.simulateParkingLoad("Monday", 8);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockParkingSpaces, response.getBody());
        verify(parkingSimulationService, times(1)).simulateParkingLoad("Monday", 8);
    }

    @Test
    void testResetParkingSpaces() {
        ResponseEntity<Void> response = parkingSimulationController.resetParkingSpaces();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(parkingSimulationService, times(1)).resetParkingSpaces();
    }
}
