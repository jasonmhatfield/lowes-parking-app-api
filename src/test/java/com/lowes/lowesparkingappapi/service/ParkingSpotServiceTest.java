package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class ParkingSpotServiceTest {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @MockBean
    private ParkingSpotRepository parkingSpotRepository;

    private ParkingSpot spot;

    @BeforeEach
    public void setUp() {
        spot = new ParkingSpot();
        spot.setId(1L);
        spot.setSpotNumber("A1");
        spot.setOccupied(false);
        spot.setType("REGULAR");
        spot.setUserId(null);
    }

    @Test
    public void testGetAllParkingSpots() {
        Mockito.when(parkingSpotRepository.findAll()).thenReturn(Collections.singletonList(spot));

        List<ParkingSpot> spots = parkingSpotService.getAllParkingSpots();
        assertNotNull(spots);
        assertEquals(1, spots.size());
        assertEquals(spot.getId(), spots.get(0).getId());
    }

    @Test
    public void testGetParkingSpotById_Found() {
        Mockito.when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.of(spot));

        ParkingSpot foundSpot = parkingSpotService.getParkingSpotById(1L);
        assertNotNull(foundSpot);
        assertEquals(spot.getId(), foundSpot.getId());
    }

    @Test
    public void testGetParkingSpotById_NotFound() {
        Mockito.when(parkingSpotRepository.findById(anyLong())).thenReturn(Optional.empty());

        ParkingSpot foundSpot = parkingSpotService.getParkingSpotById(999L);
        assertNull(foundSpot);
    }

    @Test
    public void testSaveParkingSpot() {
        Mockito.when(parkingSpotRepository.save(any(ParkingSpot.class))).thenReturn(spot);

        ParkingSpot savedSpot = parkingSpotService.saveParkingSpot(spot);
        assertNotNull(savedSpot);
        assertEquals(spot.getId(), savedSpot.getId());
        assertEquals(spot.getSpotNumber(), savedSpot.getSpotNumber());
    }
}
