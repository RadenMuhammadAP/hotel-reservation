package com.example.hotelreservation.service;

import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.exception.ReservationErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * Kelas ini digunakan untuk membungkus hasil dari operasi reservasi,
 * baik itu berhasil atau mengalami error.
 */
@Getter
@AllArgsConstructor
public class ReservationResult {

    // Menyimpan hasil reservasi jika berhasil, atau Optional.empty() jika gagal.
    private final Optional<Reservation> reservation;

    // Menyimpan jenis error jika terjadi kesalahan, atau null jika tidak ada error.
    private final ReservationErrorType error;

    /**
     * Metode statis untuk membuat objek ReservationResult yang menunjukkan bahwa operasi berhasil.
     * 
     * @param res Objek Reservation yang berhasil dibuat.
     * @return ReservationResult yang berisi reservation yang sukses.
     */
    public static ReservationResult success(Reservation res) {
        return new ReservationResult(Optional.of(res), null);
    }

    /**
     * Metode statis untuk membuat objek ReservationResult yang menunjukkan bahwa terjadi error.
     * 
     * @param error Jenis error yang terjadi.
     * @return ReservationResult yang berisi error yang terjadi.
     */
    public static ReservationResult error(ReservationErrorType error) {
        return new ReservationResult(Optional.empty(), error);
    }
}
