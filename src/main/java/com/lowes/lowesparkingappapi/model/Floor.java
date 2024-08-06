package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Floor {

    @Id
    private UUID floorId;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLot lot;

    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;

    private int floorNumber;
}
