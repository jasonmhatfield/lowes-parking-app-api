package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.Gate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class GateRepositoryTest {

    @Autowired
    private GateRepository gateRepository;

    @Test
    void testSaveAndFind() {
        gateRepository.deleteAll(); // Ensure database is clean
        Gate gate = new Gate(null, "Main Gate", true);
        gateRepository.save(gate);

        List<Gate> gates = gateRepository.findAll();
        assertEquals(1, gates.size());
        assertEquals("Main Gate", gates.get(0).getGateName());
    }
}
