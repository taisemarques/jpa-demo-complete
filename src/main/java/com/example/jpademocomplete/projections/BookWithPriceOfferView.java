package com.example.jpademocomplete.projections;

import java.util.List;
import java.util.UUID;

public interface BookWithPriceOfferView {

    UUID getBookId();

    String getTitle();

    String getDescription();

    Long getPrice();

    List<PriceOfferView> getPriceOffer();

}
