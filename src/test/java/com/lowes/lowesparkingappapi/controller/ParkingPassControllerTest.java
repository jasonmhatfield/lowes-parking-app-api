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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
