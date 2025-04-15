package com.example.hotelreservation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.example.hotelreservation.model.Availability;

/**
 * Entity ini merepresentasikan data kamar di hotel.
 * Setiap kamar memiliki tipe, harga, status ketersediaan, dan informasi waktu pembuatan dan pembaruan.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID unik untuk setiap kamar

    private String roomType; // Tipe kamar, misalnya Single, Double, dll
    private Double price; // Harga per malam untuk kamar

    @Enumerated(EnumType.STRING)
    private Availability availability; // Status ketersediaan kamar (AVAILABLE/BOOKED)

    private LocalDateTime createdAt; // Waktu ketika kamar dibuat
    private LocalDateTime updatedAt; // Waktu ketika kamar terakhir kali diperbarui

    /**
     * Metode ini dipanggil sebelum entitas disimpan ke database (PrePersist).
     * Digunakan untuk mengatur waktu pembuatan dan pembaruan, serta status ketersediaan kamar.
     */
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now(); // Set waktu pembuatan kamar
        updatedAt = LocalDateTime.now(); // Set waktu pembaruan kamar
        if (availability == null) availability = Availability.AVAILABLE; // Set default availability ke AVAILABLE jika null
    }

    /**
     * Metode ini dipanggil sebelum entitas diperbarui di database (PreUpdate).
     * Digunakan untuk memperbarui waktu terakhir kali entitas diperbarui.
     */
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(); // Set waktu pembaruan kamar
    }
}
