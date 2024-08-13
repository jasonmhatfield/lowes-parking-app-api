package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ParkingSpotRepositoryTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    private ParkingSpot spot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        spot = new ParkingSpot();
        spot.setSpotNumber("A1");
        spot.setOccupied(false);
        spot.setType("REGULAR");
        spot.setUserId(null);
    }

    @Test
    public void testSaveParkingSpot() {
        spot.setId(1L);
        when(parkingSpotRepository.save(any(ParkingSpot.class))).thenReturn(spot);

        ParkingSpot savedSpot = parkingSpotRepository.save(spot);
        assertNotNull(savedSpot.getId());
        assertEquals("A1", savedSpot.getSpotNumber());
    }

    @Test
    public void testFindAllParkingSpots() {
        when(parkingSpotRepository.findAll()).thenReturn(Collections.singletonList(spot));

        List<ParkingSpot> spots = parkingSpotRepository.findAll();
        assertFalse(spots.isEmpty());
        assertEquals(1, spots.size());
    }

    @Test
    public void testFindParkingSpotById() {
        spot.setId(1L);
        when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.of(spot));

        Optional<ParkingSpot> foundSpot = parkingSpotRepository.findById(spot.getId());
        assertTrue(foundSpot.isPresent());
        assertEquals("A1", foundSpot.get().getSpotNumber());
    }

    @Test
    public void testDeleteParkingSpot() {
        doNothing().when(parkingSpotRepository).deleteById(anyLong());

        parkingSpotRepository.deleteById(spot.getId());
        verify(parkingSpotRepository, times(1)).deleteById(spot.getId());
    }
}
