package com.example.jpademocomplete.projections;

import java.util.List;
import java.util.UUID;

public interface BookWithReservations {

    UUID getBookId();

    String getTitle();

    String getDescription();

    Long getPrice();

    ReservationView getReservation();

}
