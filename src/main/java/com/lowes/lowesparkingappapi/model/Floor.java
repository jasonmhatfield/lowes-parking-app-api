package com.lowes.lowesparkingappapi.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.UUID;

@Entity
@Data
public class Floor {
    @Id
    private UUID floorId;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLot lot;

    @ManyToOne
    @JoinColumn(name = "gate_id")  // Update this line to match the column name in the schema
    private Gate gate;

    private int floorNumber;
}
