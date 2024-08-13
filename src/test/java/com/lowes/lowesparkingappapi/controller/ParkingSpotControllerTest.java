package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.service.ParkingSpotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingSpotController.class)
public class ParkingSpotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingSpotService parkingSpotService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

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
    public void testGetAllParkingSpots() throws Exception {
        Mockito.when(parkingSpotService.getAllParkingSpots()).thenReturn(Collections.singletonList(spot));

        mockMvc.perform(get("/api/parkingSpots"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'id':1,'spotNumber':'A1','occupied':false,'type':'REGULAR','userId':null}]"));
    }

    @Test
    public void testUpdateParkingSpot_Occupied() throws Exception {
        spot.setOccupied(true);
        spot.setUserId(2L);
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(spot);
        Mockito.when(parkingSpotService.saveParkingSpot(any(ParkingSpot.class))).thenReturn(spot);

        mockMvc.perform(patch("/api/parkingSpots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"occupied\":true, \"userId\":2}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'id':1,'spotNumber':'A1','occupied':true,'type':'REGULAR','userId':2}"));

        Mockito.verify(messagingTemplate).convertAndSend("/topic/parkingSpots", spot);
    }

    @Test
    public void testUpdateParkingSpot_OccupiedWithoutUserId() throws Exception {
        spot.setOccupied(true);
        spot.setUserId(null);
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(spot);
        Mockito.when(parkingSpotService.saveParkingSpot(any(ParkingSpot.class))).thenReturn(spot);

        mockMvc.perform(patch("/api/parkingSpots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"occupied\":true}"))  // No userId provided
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'id':1,'spotNumber':'A1','occupied':true,'type':'REGULAR','userId':null}",
                        true));

        Mockito.verify(messagingTemplate).convertAndSend("/topic/parkingSpots", spot);
    }

    @Test
    public void testUpdateParkingSpot_NotOccupied() throws Exception {
        spot.setOccupied(false);
        spot.setUserId(null);
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(spot);
        Mockito.when(parkingSpotService.saveParkingSpot(any(ParkingSpot.class))).thenReturn(spot);

        mockMvc.perform(patch("/api/parkingSpots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"occupied\":false}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'id':1,'spotNumber':'A1','occupied':false,'type':'REGULAR','userId':null}"));

        Mockito.verify(messagingTemplate).convertAndSend("/topic/parkingSpots", spot);
    }

    @Test
    public void testUpdateParkingSpot_NotFound() throws Exception {
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(null);

        mockMvc.perform(patch("/api/parkingSpots/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"occupied\":true, \"userId\":2}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateParkingSpot_BadRequest() throws Exception {
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(spot);

        mockMvc.perform(patch("/api/parkingSpots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateParkingSpot_InvalidUserId() throws Exception {
        spot.setOccupied(true);
        spot.setUserId(null);
        Mockito.when(parkingSpotService.getParkingSpotById(anyLong())).thenReturn(spot);
        Mockito.when(parkingSpotService.saveParkingSpot(any(ParkingSpot.class))).thenReturn(spot);

        mockMvc.perform(patch("/api/parkingSpots/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"occupied\":true, \"userId\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'id':1,'spotNumber':'A1','occupied':true,'type':'REGULAR','userId':null}",
                        true));

        Mockito.verify(messagingTemplate).convertAndSend("/topic/parkingSpots", spot);
    }
}
