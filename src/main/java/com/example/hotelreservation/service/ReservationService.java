package com.example.hotelreservation.service;

import com.example.hotelreservation.model.*;
import com.example.hotelreservation.repository.ReservationRepository;
import com.example.hotelreservation.repository.RoomRepository;
import com.example.hotelreservation.exception.ReservationErrorType;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

/**
 * Service yang menangani logika bisnis untuk pemesanan (reservation).
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;

    public ReservationService(ReservationRepository reservationRepo, RoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }

    /**
     * Membuat reservasi baru jika tidak terjadi konflik tanggal dan kamar tersedia.
     *
     * @param reservation data reservasi yang akan dibuat
     * @return hasil dari proses reservasi, bisa sukses atau gagal dengan jenis error
     */
    public ReservationResult createReservation(Reservation reservation) {
        Room room = reservation.getRoom();

        // Cek apakah kamar yang dimaksud tersedia di database
        if (room != null) {
            room = roomRepo.findById(room.getId()).orElse(null);
        }
        if (room == null) {
            return ReservationResult.error(ReservationErrorType.ROOM_NOT_FOUND);
        }

        // Cek apakah ada reservasi aktif lain yang tanggalnya bertabrakan
        List<Reservation> existingReservations = reservationRepo.findByRoomIdAndStatus(room.getId(), ReservationStatus.RESERVED);
        for (Reservation existing : existingReservations) {
            boolean isOverlap =
                !(reservation.getCheckOutDate().isBefore(existing.getCheckInDate()) ||
                  reservation.getCheckInDate().isAfter(existing.getCheckOutDate()));
            if (isOverlap) {
                return ReservationResult.error(ReservationErrorType.DATE_CONFLICT);
            }
        }

        // Tandai kamar sebagai dipesan
        room.setAvailability(Availability.BOOKED);
        roomRepo.save(room);

        // Simpan reservasi dengan status RESERVED
        reservation.setRoom(room);
        reservation.setStatus(ReservationStatus.RESERVED);
        return ReservationResult.success(reservationRepo.save(reservation));
    }

    /**
     * Mengambil detail reservasi berdasarkan ID.
     *
     * @param id ID dari reservasi
     * @return Optional<Reservation> jika ditemukan
     */
    public Optional<Reservation> getReservation(Long id) {
        return reservationRepo.findByIdWithRoom(id);
    }

    /**
     * Membatalkan reservasi berdasarkan ID dan mengubah status kamar menjadi AVAILABLE kembali.
     *
     * @param id ID dari reservasi yang akan dibatalkan
     * @return Reservasi yang telah dibatalkan
     */
    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepo.findByIdWithRoom(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepo.save(reservation);

        // Set status kamar kembali menjadi tersedia
        Room room = roomRepo.findById(reservation.getRoom().getId()).orElse(null);
        if (room != null) {
            room.setAvailability(Availability.AVAILABLE);
            roomRepo.save(room);
        }

        return reservation;
    }
}

