package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.hotelreservation.service.ReservationResult;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
	
    @PostMapping
	public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
		ReservationResult result = reservationService.createReservation(reservation);
		//	logger.info("Error=>",created);
		if (result.getReservation().isPresent()) {
			return ResponseEntity.ok(result.getReservation().get());
		}

		return switch (result.getError()) {
			case ROOM_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Room not available"));
			case DATE_CONFLICT -> ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Dates overlap with existing reservation"));
		};
	}
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @PutMapping("/{id}")
    public Reservation cancelReservation(@PathVariable Long id) {
        return reservationService.cancelReservation(id);
    }
}
