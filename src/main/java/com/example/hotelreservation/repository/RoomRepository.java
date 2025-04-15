package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository ini menyediakan akses ke entitas Room
 * untuk melakukan operasi CRUD (Create, Read, Update, Delete).
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    // JpaRepository sudah menyediakan implementasi dasar untuk operasi CRUD.
    // Tidak perlu menambahkan query khusus kecuali diperlukan untuk operasi kompleks lainnya.
}
