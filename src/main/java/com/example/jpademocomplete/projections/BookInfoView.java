package com.example.jpademocomplete.projections;

import java.util.List;
import java.util.UUID;

public interface BookInfoView {

    UUID getBookId();

    String getTitle();

    String getDescription();

    Long getPrice();

    List<AuthorView> getAuthors();

    List<PriceOfferView> getPriceOffer();

    List<ReviewView> getReviews();

    ReservationView getReservation();

}
