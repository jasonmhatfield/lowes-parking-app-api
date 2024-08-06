package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingPassTest {

    @Test
    void testSetAndGetPassId() {
        ParkingPass parkingPass = new ParkingPass();
        UUID passId = UUID.randomUUID();
        parkingPass.setPassId(passId);

        assertNotNull(parkingPass.getPassId());
        assertEquals(passId, parkingPass.getPassId());
    }

    @Test
    void testSetAndGetUser() {
        ParkingPass parkingPass = new ParkingPass();
        User user = new User();
        user.setUserId(UUID.randomUUID());
        parkingPass.setUser(user);

        assertNotNull(parkingPass.getUser());
        assertEquals(user, parkingPass.getUser());
    }

    @Test
    void testSetAndGetIssueDate() {
        ParkingPass parkingPass = new ParkingPass();
        LocalDate issueDate = LocalDate.now();
        parkingPass.setIssueDate(issueDate);

        assertNotNull(parkingPass.getIssueDate());
        assertEquals(issueDate, parkingPass.getIssueDate());
    }

    @Test
    void testSetAndGetExpiryDate() {
        ParkingPass parkingPass = new ParkingPass();
        LocalDate expiryDate = LocalDate.now().plusYears(1);
        parkingPass.setExpiryDate(expiryDate);

        assertNotNull(parkingPass.getExpiryDate());
        assertEquals(expiryDate, parkingPass.getExpiryDate());
    }
}
