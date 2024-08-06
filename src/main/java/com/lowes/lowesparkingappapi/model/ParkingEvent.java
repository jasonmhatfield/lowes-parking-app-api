package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ParkingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Gate gate;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String eventName;
}
