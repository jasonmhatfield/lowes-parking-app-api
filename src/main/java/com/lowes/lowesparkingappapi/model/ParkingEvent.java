package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ParkingEvent {
    @Id
    private UUID eventId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Gate gate;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
