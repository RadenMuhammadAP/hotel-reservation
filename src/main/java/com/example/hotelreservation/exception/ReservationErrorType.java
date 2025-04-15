package com.example.hotelreservation.exception;

/**
 * Enum ini merepresentasikan jenis-jenis error
 * yang mungkin terjadi saat membuat reservasi kamar.
 */
public enum ReservationErrorType {
    
    // Error ketika kamar tidak ditemukan di database
    ROOM_NOT_FOUND,

    // Error ketika tanggal check-in dan check-out bertabrakan dengan reservasi yang sudah ada
    DATE_CONFLICT
}
