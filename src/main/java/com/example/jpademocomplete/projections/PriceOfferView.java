package com.example.jpademocomplete.projections;

import java.util.Date;
import java.util.UUID;

public interface PriceOfferView {

    UUID getPriceOfferId();

    Long getNewPrice();

    String getPromotionalText();

    Date getCreation();

}
