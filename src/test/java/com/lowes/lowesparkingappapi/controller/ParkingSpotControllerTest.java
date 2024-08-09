package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.service.ParkingSpotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingSpotControllerTest {

    @Mock
    private ParkingSpotService parkingSpotService;

    @InjectMocks
    private ParkingSpotController parkingSpotController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllParkingSpots() {
        // Arrange
        List<ParkingSpot> parkingSpots = Arrays.asList(
                new ParkingSpot(1L, "A1", false, "regular", null),
                new ParkingSpot(2L, "B1", true, "handicap", 123L)
        );
        when(parkingSpotService.getAllParkingSpots()).thenReturn(parkingSpots);

        // Act
        List<ParkingSpot> result = parkingSpotController.getAllParkingSpots();

        // Assert
        assertEquals(2, result.size());
        assertEquals("A1", result.get(0).getSpotNumber());
        assertEquals("B1", result.get(1).getSpotNumber());

        verify(parkingSpotService, times(1)).getAllParkingSpots();
    }

    @Test
    void testUpdateParkingSpot_notFound() {
        // Scenario: spot is null, which means it's not found
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(null);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
    }

    @Test
    void testUpdateParkingSpot_isOccupiedNotProvided() {
        // Scenario: "isOccupied" is not in the updates map
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>(); // "isOccupied" not provided

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_userIdProvidedAndOccupied() {
        // Scenario: "isOccupied" and non-null "userId" are both provided
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);
        updates.put("userId", 123L);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(spot.isOccupied());
        assertEquals(123L, spot.getUserId());

        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_userIdNullAndOccupied() {
        // Scenario: "isOccupied" is true but "userId" is null
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);
        updates.put("userId", null);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(spot.isOccupied());
        assertNull(spot.getUserId());

        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_userIdNotProvided() {
        // Scenario: "isOccupied" is true but "userId" is not provided in the updates map
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true); // "userId" not provided

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(spot.isOccupied());
        assertNull(spot.getUserId());

        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_AlreadyOccupiedByAnotherUser() {
        // Scenario: spot is already occupied by a different user
        ParkingSpot spot = new ParkingSpot(1L, "A1", true, "regular", 456L);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);
        updates.put("userId", 123L);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(0)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_SameUserOccupying() {
        // Scenario: spot is already occupied by the same user
        ParkingSpot spot = new ParkingSpot(1L, "A1", true, "regular", 123L);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);
        updates.put("userId", 123L);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(spot.isOccupied());
        assertEquals(123L, spot.getUserId());

        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_isOccupiedFalse() {
        // Scenario: "isOccupied" is false, should clear userId
        ParkingSpot spot = new ParkingSpot(1L, "A1", true, "regular", 123L);
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", false);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(spot.isOccupied());
        assertNull(spot.getUserId());

        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(1)).saveParkingSpot(spot);
    }

    @Test
    void testUpdateParkingSpot_isOccupiedTrue_userIdNull() {
        // Scenario: The spot is occupied, but the userId is null, covering the branch where spot.getUserId() == null
        ParkingSpot spot = new ParkingSpot(1L, "A1", true, "regular", null);  // userId is null
        when(parkingSpotService.getParkingSpotById(1L)).thenReturn(spot);

        Map<String, Object> updates = new HashMap<>();
        updates.put("isOccupied", true);
        updates.put("userId", 123L);

        ResponseEntity<ParkingSpot> response = parkingSpotController.updateParkingSpot(1L, updates);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verify(parkingSpotService, times(1)).getParkingSpotById(1L);
        verify(parkingSpotService, times(0)).saveParkingSpot(spot);
    }
}
