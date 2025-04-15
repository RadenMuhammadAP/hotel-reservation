package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.Room;
import com.example.hotelreservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller untuk mengelola data kamar (Room) di hotel.
 */
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * Menambahkan kamar baru ke dalam sistem.
     *
     * @param room objek Room yang dikirim dari request body
     * @return objek Room yang berhasil ditambahkan
     */
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    /**
     * Mengambil daftar semua kamar yang tersedia.
     *
     * @return list dari semua Room
     */
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
