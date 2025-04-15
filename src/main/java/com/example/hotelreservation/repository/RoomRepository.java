package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}