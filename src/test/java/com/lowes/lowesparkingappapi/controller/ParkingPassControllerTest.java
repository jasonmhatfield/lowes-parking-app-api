package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.service.ParkingPassService;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingPassControllerTest {

    @Mock
    private ParkingPassService parkingPassService;

    @InjectMocks
    private ParkingPassController parkingPassController;

    @Test
    void testAssignParkingPass() {
        ParkingPassDto parkingPassDto = ParkingPassDto.builder().build();

        ResponseEntity<Void> response = parkingPassController.assignParkingPass(parkingPassDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(parkingPassService, times(1)).assignParkingPass(parkingPassDto);
    }

    @Test
    void testGetAllParkingPasses() {
        List<ParkingPassDto> mockPasses = List.of(ParkingPassDto.builder().build());
        when(parkingPassService.getAllParkingPasses()).thenReturn(mockPasses);

        ResponseEntity<List<ParkingPassDto>> response = parkingPassController.getAllParkingPasses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPasses, response.getBody());
        verify(parkingPassService, times(1)).getAllParkingPasses();
    }

    @Test
    void testGetParkingPassByUserId_Found() {
        UUID userId = UUID.randomUUID();
        ParkingPassDto mockPass = ParkingPassDto.builder().build();
        when(parkingPassService.getParkingPassByUserId(userId)).thenReturn(mockPass);

        ResponseEntity<ParkingPassDto> response = parkingPassController.getParkingPassByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPass, response.getBody());
        verify(parkingPassService, times(1)).getParkingPassByUserId(userId);
    }

    @Test
    void testGetParkingPassByUserId_NotFound() {
        UUID userId = UUID.randomUUID();
        when(parkingPassService.getParkingPassByUserId(userId)).thenReturn(null);

        ResponseEntity<ParkingPassDto> response = parkingPassController.getParkingPassByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(parkingPassService, times(1)).getParkingPassByUserId(userId);
    }

    @Test
    void testUpdateParkingPass_Success() {
        UUID passId = UUID.randomUUID();
        ParkingPassDto parkingPassDto = ParkingPassDto.builder().build();
        when(parkingPassService.updateParkingPass(passId, parkingPassDto)).thenReturn(true);

        ResponseEntity<Void> response = parkingPassController.updateParkingPass(passId, parkingPassDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(parkingPassService, times(1)).updateParkingPass(passId, parkingPassDto);
    }

    @Test
    void testUpdateParkingPass_NotFound() {
        UUID passId = UUID.randomUUID();
        ParkingPassDto parkingPassDto = ParkingPassDto.builder().build();
        when(parkingPassService.updateParkingPass(passId, parkingPassDto)).thenReturn(false);

        ResponseEntity<Void> response = parkingPassController.updateParkingPass(passId, parkingPassDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(parkingPassService, times(1)).updateParkingPass(passId, parkingPassDto);
    }
}
