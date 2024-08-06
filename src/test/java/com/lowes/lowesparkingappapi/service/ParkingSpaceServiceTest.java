package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.repository.ParkingSpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingSpaceServiceTest {

    @Mock
    private ParkingSpaceRepository parkingSpaceRepository;

    @InjectMocks
    private ParkingSpaceService parkingSpaceService;

    private ParkingSpace parkingSpace;

    @BeforeEach
    void setUp() {
        parkingSpace = new ParkingSpace();
        parkingSpace.setSpaceId(UUID.randomUUID());
        parkingSpace.setSpaceNumber("A1");
        parkingSpace.setOccupied(false);
    }

    @Test
    void testGetAllParkingSpaces() {
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace));

        List<ParkingSpace> result = parkingSpaceService.getAllParkingSpaces();

        assertEquals(1, result.size());
        assertEquals(parkingSpace, result.get(0));
        verify(parkingSpaceRepository, times(1)).findAll();
    }
}
