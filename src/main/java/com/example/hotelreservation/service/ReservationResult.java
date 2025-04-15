package com.example.hotelreservation.service;

import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.exception.ReservationErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class ReservationResult {
    private final Optional<Reservation> reservation;
    private final ReservationErrorType error;
    
    public static ReservationResult success(Reservation res) {
        return new ReservationResult(Optional.of(res), null);
    }

    public static ReservationResult error(ReservationErrorType error) {
        return new ReservationResult(Optional.empty(), error);
    }
}
