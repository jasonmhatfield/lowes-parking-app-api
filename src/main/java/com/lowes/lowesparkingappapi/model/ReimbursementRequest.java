package com.lowes.lowesparkingappapi.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ReimbursementRequest {
    @Id
    private UUID requestId;

    @ManyToOne
    private User user;

    private LocalDateTime date;
    private double amount;
    private String status;  // should be Enum (pending, approved, rejected)
}
