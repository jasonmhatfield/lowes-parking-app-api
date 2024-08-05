package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Gate {
    @Id
    @GeneratedValue
    private UUID gateId;

    private String gateName;
    private Boolean isOperational;
    private Boolean isRestricted;

    @ManyToOne
    @JoinColumn(name = "lot_id", nullable = false)
    private ParkingLot lot;
}
