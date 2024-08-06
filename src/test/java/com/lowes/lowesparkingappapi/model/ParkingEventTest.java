package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingEventTest {

    @Test
    void testSetAndGetEventId() {
        ParkingEvent event = new ParkingEvent();
        UUID eventId = UUID.randomUUID();
        event.setEventId(eventId);

        assertNotNull(event.getEventId());
        assertEquals(eventId, event.getEventId());
    }

    @Test
    void testSetAndGetUser() {
        ParkingEvent event = new ParkingEvent();
        User user = new User();
        user.setUserId(UUID.randomUUID());
        event.setUser(user);

        assertNotNull(event.getUser());
        assertEquals(user.getUserId(), event.getUser().getUserId());
    }

    @Test
    void testSetAndGetGate() {
        ParkingEvent event = new ParkingEvent();
        Gate gate = new Gate();
        gate.setGateId(UUID.randomUUID());
        event.setGate(gate);

        assertNotNull(event.getGate());
        assertEquals(gate.getGateId(), event.getGate().getGateId());
    }

    @Test
    void testSetAndGetEntryTime() {
        ParkingEvent event = new ParkingEvent();
        LocalDateTime entryTime = LocalDateTime.now();
        event.setEntryTime(entryTime);

        assertNotNull(event.getEntryTime());
        assertEquals(entryTime, event.getEntryTime());
    }

    @Test
    void testSetAndGetExitTime() {
        ParkingEvent event = new ParkingEvent();
        LocalDateTime exitTime = LocalDateTime.now().plusHours(1);
        event.setExitTime(exitTime);

        assertNotNull(event.getExitTime());
        assertEquals(exitTime, event.getExitTime());
    }

    @Test
    void testSetAndGetEventName() {
        ParkingEvent event = new ParkingEvent();
        String eventName = "Parking Event";
        event.setEventName(eventName);

        assertNotNull(event.getEventName());
        assertEquals(eventName, event.getEventName());
    }
}
