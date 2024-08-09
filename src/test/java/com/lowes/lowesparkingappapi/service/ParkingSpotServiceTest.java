package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingSpotServiceTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllParkingSpots() {
        // Scenario: Test fetching all parking spots
        parkingSpotService.getAllParkingSpots();
        verify(parkingSpotRepository, times(1)).findAll();
    }

    @Test
    void testGetParkingSpotById_existingId() {
        // Scenario: Parking spot is found
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotRepository.findById(1L)).thenReturn(Optional.of(spot));

        ParkingSpot result = parkingSpotService.getParkingSpotById(1L);

        assertNotNull(result);
        assertEquals(spot, result);
        verify(parkingSpotRepository, times(1)).findById(1L);
    }

    @Test
    void testGetParkingSpotById_nonExistingId() {
        // Scenario: Parking spot is not found
        when(parkingSpotRepository.findById(1L)).thenReturn(Optional.empty());

        ParkingSpot result = parkingSpotService.getParkingSpotById(1L);

        assertNull(result);
        verify(parkingSpotRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveParkingSpot() {
        // Scenario: Save a parking spot
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotRepository.save(spot)).thenReturn(spot);

        ParkingSpot result = parkingSpotService.saveParkingSpot(spot);

        assertNotNull(result);
        assertEquals(spot, result);
        verify(parkingSpotRepository, times(1)).save(spot);
    }

    @Test
    void testUpdateParkingSpot_whenOccupied() {
        // Scenario: Update parking spot to occupied
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotRepository.findById(1L)).thenReturn(Optional.of(spot));
        when(parkingSpotRepository.save(spot)).thenReturn(spot);

        ParkingSpot updatedSpot = parkingSpotService.updateParkingSpot(1L, true, 123L);

        assertTrue(updatedSpot.isOccupied());
        assertEquals(123L, updatedSpot.getUserId());

        verify(parkingSpotRepository, times(1)).findById(1L);
        verify(parkingSpotRepository, times(1)).save(spot);
    }

    @Test
    void testUpdateParkingSpot_whenNotOccupied() {
        // Scenario: Update parking spot to not occupied
        ParkingSpot spot = new ParkingSpot(1L, "A1", true, "regular", 123L);
        when(parkingSpotRepository.findById(1L)).thenReturn(Optional.of(spot));
        when(parkingSpotRepository.save(spot)).thenReturn(spot);

        ParkingSpot updatedSpot = parkingSpotService.updateParkingSpot(1L, false, 123L);

        assertFalse(updatedSpot.isOccupied());
        assertNull(updatedSpot.getUserId());

        verify(parkingSpotRepository, times(1)).findById(1L);
        verify(parkingSpotRepository, times(1)).save(spot);
    }
}
