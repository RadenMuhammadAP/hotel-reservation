package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.example.hotelreservation.model.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r JOIN FETCH r.room WHERE r.id = :id")
	Optional<Reservation> findByIdWithRoom(@Param("id") Long id);
    List<Reservation> findByRoomIdAndStatus(Long roomId, ReservationStatus status);
		
}