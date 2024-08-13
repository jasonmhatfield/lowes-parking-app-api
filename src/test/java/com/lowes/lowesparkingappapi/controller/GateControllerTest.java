package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GateController.class)
public class GateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GateService gateService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    private Gate gate;

    @BeforeEach
    public void setUp() {
        gate = new Gate();
        gate.setId(1L);
        gate.setGateName("Main Gate");
        gate.setOperational(true);
    }

    @Test
    public void testGetAllGates() throws Exception {
        Mockito.when(gateService.getAllGates()).thenReturn(Collections.singletonList(gate));

        mockMvc.perform(get("/api/gates"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'gateName':'Main Gate','operational':true}]"));
    }

    @Test
    public void testUpdateGateStatus() throws Exception {
        Mockito.when(gateService.updateGateStatus(anyLong(), anyBoolean())).thenReturn(gate);

        mockMvc.perform(patch("/api/gates/1")
                        .param("isOperational", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'gateName':'Main Gate','operational':true}"));

        Mockito.verify(messagingTemplate).convertAndSend("/topic/gates", gate);
    }
}
