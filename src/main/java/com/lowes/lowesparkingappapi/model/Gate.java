package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
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
