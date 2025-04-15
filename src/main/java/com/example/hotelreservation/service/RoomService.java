package com.example.hotelreservation.service;

import com.example.hotelreservation.model.Room;
import com.example.hotelreservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class yang menangani logika bisnis terkait data Room (kamar).
 */
@Service
@RequiredArgsConstructor
public class RoomService {

    // Dependency injection untuk repository Room
    private final RoomRepository roomRepository;

    /**
     * Menambahkan data kamar baru ke database.
     *
     * @param room data kamar yang ingin ditambahkan
     * @return Room yang telah disimpan
     */
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    /**
     * Mengambil seluruh data kamar dari database.
     *
     * @return List berisi seluruh Room
     */
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Mengambil data kamar berdasarkan ID.
     *
     * @param id ID kamar
     * @return Room jika ditemukan, jika tidak maka null
     */
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    /**
     * Memperbarui data kamar yang sudah ada.
     *
     * @param room data kamar yang sudah dimodifikasi
     */
    public void updateRoom(Room room) {
        roomRepository.save(room); // save() juga berfungsi untuk update jika ID sudah ada
    }
}
