package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.service.ParkingSpaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingSpaceControllerTest {

    @Mock
    private ParkingSpaceService parkingSpaceService;

    @InjectMocks
    private ParkingSpaceController parkingSpaceController;

    @Test
    void testGetAllParkingSpaces() {
        List<ParkingSpace> mockSpaces = List.of(new ParkingSpace());
        when(parkingSpaceService.getAllParkingSpaces()).thenReturn(mockSpaces);

        List<ParkingSpace> response = parkingSpaceController.getAllParkingSpaces();

        assertEquals(mockSpaces, response);
        verify(parkingSpaceService, times(1)).getAllParkingSpaces();
    }
}
