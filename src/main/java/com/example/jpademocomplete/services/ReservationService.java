package com.example.jpademocomplete.services;

import com.example.jpademocomplete.bo.ReservationBO;
import com.example.jpademocomplete.entities.Book;
import com.example.jpademocomplete.entities.Reservation;
import com.example.jpademocomplete.repositories.ReservationRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    private Converter<ReservationBO, Reservation> reservationBOToReservation = in -> {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(in.getReservationDate());
        reservation.setDescription(in.getDescription());
        return reservation;
    };

    public Converter<Reservation, ReservationBO> reservationToReservationBO = in -> {
        ReservationBO reservation = new ReservationBO();
        reservation.setReservationDate(in.getReservationDate());
        reservation.setDescription(in.getDescription());
        return reservation;
    };

    ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(ReservationBO reservationBO, Book book) {
        Reservation reservation = reservationBOToReservation.convert(reservationBO);
        reservation.setReservationCreation(new Date());
        reservation.setBook(book);
        return reservationRepository.save(reservation);
    }

}
