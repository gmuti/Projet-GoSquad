package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();

    Boolean createReservation(Reservation reservation);

    Boolean updateReservation(Reservation reservation);

    Boolean deleteReservation(String id);
}
