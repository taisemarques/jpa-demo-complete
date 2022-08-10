package com.example.jpademocomplete.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public interface ReservationView {

    UUID getReservationId();

    String getDescription();

    Date getReservationDate();

    Date getReservationCreation();

    @Value("#{(target.reservationDate.getTime() - target.reservationCreation.getTime()) / (1000*60*60*24)}")
    int getDaysToExpire();

}
