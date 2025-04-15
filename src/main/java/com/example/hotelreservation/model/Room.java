package com.example.hotelreservation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.example.hotelreservation.model.Availability;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomType;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (availability == null) availability = Availability.AVAILABLE;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}