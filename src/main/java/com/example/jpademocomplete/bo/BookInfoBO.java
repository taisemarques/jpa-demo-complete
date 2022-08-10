package com.example.jpademocomplete.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoBO {

    private String title;

    private String description;

    private Long price;

    List<AuthorBO> authors;

    List<PriceOfferBO> priceOffers;

    List<ReviewBO> reviews;

    ReservationBO reservation;
}
