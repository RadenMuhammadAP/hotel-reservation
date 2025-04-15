package com.example.hotelreservation.service;

import com.example.hotelreservation.model.*;
import com.example.hotelreservation.repository.ReservationRepository;
import com.example.hotelreservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;
import com.example.hotelreservation.model.ReservationStatus;
import com.example.hotelreservation.exception.ReservationErrorType;
import com.example.hotelreservation.service.ReservationResult;
import org.springframework.http.HttpStatus;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;

    public ReservationService(ReservationRepository reservationRepo, RoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }
	
	public ReservationResult createReservation(Reservation reservation) {
		Room room = reservation.getRoom();
		if (room != null) {
			room = roomRepo.findById(room.getId()).orElse(null);
		}
		if (room == null) {
			return ReservationResult.error(ReservationErrorType.ROOM_NOT_FOUND);
		}
		List<Reservation> existingReservations = reservationRepo.findByRoomIdAndStatus(room.getId(), ReservationStatus.RESERVED);
		for (Reservation existing : existingReservations) {
			boolean isOverlap =
				!(reservation.getCheckOutDate().isBefore(existing.getCheckInDate()) ||
				  reservation.getCheckInDate().isAfter(existing.getCheckOutDate()));
			if (isOverlap) {
				 return ReservationResult.error(ReservationErrorType.DATE_CONFLICT);
			}
		}
		room.setAvailability(Availability.BOOKED);
		roomRepo.save(room);
		reservation.setRoom(room);
		reservation.setStatus(ReservationStatus.RESERVED);
		return ReservationResult.success(reservationRepo.save(reservation));
	}
	
    public Optional<Reservation> getReservation(Long id) {
        return reservationRepo.findByIdWithRoom(id);
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepo.findByIdWithRoom(id).orElseThrow(() -> new RuntimeException("Not found"));

        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepo.save(reservation);

        Room room = roomRepo.findById(reservation.getRoom().getId()).orElse(null);
        if (room != null) {
            room.setAvailability(Availability.AVAILABLE);
            roomRepo.save(room);
        }
        return reservation;
    }
}