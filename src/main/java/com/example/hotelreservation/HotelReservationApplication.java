package com.example.hotelreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kelas utama untuk menjalankan aplikasi Spring Boot.
 * Menandai kelas ini sebagai aplikasi Spring Boot dan menjalankan aplikasi.
 */
@SpringBootApplication
public class HotelReservationApplication {

    /**
     * Metode utama untuk menjalankan aplikasi.
     * Ini adalah titik masuk aplikasi Spring Boot.
     * 
     * @param args Argumen dari baris perintah (command-line arguments).
     */
    public static void main(String[] args) {
        // Menjalankan aplikasi Spring Boot dengan kelas utama HotelReservationApplication
        SpringApplication.run(HotelReservationApplication.class, args);
    }
}
