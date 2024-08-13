package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.Gate;
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

public class GateRepositoryTest {

    @Mock
    private GateRepository gateRepository;

    private Gate gate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gate = new Gate();
        gate.setGateName("Main Gate");
        gate.setOperational(true);
    }

    @Test
    public void testSaveGate() {
        gate.setId(1L);
        when(gateRepository.save(any(Gate.class))).thenReturn(gate);

        Gate savedGate = gateRepository.save(gate);
        assertNotNull(savedGate.getId());
        assertEquals("Main Gate", savedGate.getGateName());
    }

    @Test
    public void testFindAllGates() {
        when(gateRepository.findAll()).thenReturn(Collections.singletonList(gate));

        List<Gate> gates = gateRepository.findAll();
        assertFalse(gates.isEmpty());
        assertEquals(1, gates.size());
    }

    @Test
    public void testFindGateById() {
        gate.setId(1L);
        when(gateRepository.findById(anyLong())).thenReturn(Optional.of(gate));

        Optional<Gate> foundGate = gateRepository.findById(gate.getId());
        assertTrue(foundGate.isPresent());
        assertEquals("Main Gate", foundGate.get().getGateName());
    }

    @Test
    public void testDeleteGate() {
        doNothing().when(gateRepository).deleteById(anyLong());

        gateRepository.deleteById(gate.getId());
        verify(gateRepository, times(1)).deleteById(gate.getId());
    }
}
