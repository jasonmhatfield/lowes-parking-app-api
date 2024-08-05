package com.lowes.lowesparkingappapi.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class ParkingLot {
    @Id
    private UUID lotId;

    private String name;
}
