package com.lowes.lowesparkingappapi.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class ParkingPass {

    @Id
    @GeneratedValue
    private UUID passId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate issueDate;
    private LocalDate expiryDate;
}
