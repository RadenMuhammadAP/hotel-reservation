package com.example.hotelreservation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity yang merepresentasikan data reservasi kamar hotel.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    // ID unik untuk setiap reservasi (auto-increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nama pelanggan yang melakukan reservasi
    private String customerName;

    // Relasi Many-to-One ke entitas Room (satu kamar bisa memiliki banyak reservasi)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    // Tanggal check-in
    private LocalDate checkInDate;

    // Tanggal check-out
    private LocalDate checkOutDate;

    // Status reservasi (RESERVED, CANCELED, dll.)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // Getter dan Setter untuk relasi Room
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
