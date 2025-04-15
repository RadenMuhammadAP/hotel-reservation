package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.example.hotelreservation.model.ReservationStatus;

/**
 * Repository ini menyediakan akses ke entitas Reservation
 * untuk melakukan operasi CRUD dan query khusus.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Mencari Reservation berdasarkan ID dengan menggabungkan (join) data Room.
     * Menggunakan JPQL (Java Persistence Query Language) untuk mengambil Reservation dengan Room terkait.
     * 
     * @param id ID dari Reservation yang dicari.
     * @return Optional<Reservation> berisi Reservation yang ditemukan atau kosong jika tidak ditemukan.
     */
    @Query("SELECT r FROM Reservation r JOIN FETCH r.room WHERE r.id = :id")
    Optional<Reservation> findByIdWithRoom(@Param("id") Long id);

    /**
     * Mencari daftar Reservation berdasarkan ID kamar dan status reservasi.
     * 
     * @param roomId ID kamar yang digunakan untuk mencari reservasi.
     * @param status Status dari reservasi (misalnya, RESERVED atau CANCELED).
     * @return Daftar Reservation yang cocok dengan ID kamar dan status.
     */
    List<Reservation> findByRoomIdAndStatus(Long roomId, ReservationStatus status);
}
