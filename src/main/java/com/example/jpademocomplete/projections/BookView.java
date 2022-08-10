package com.example.jpademocomplete.projections;

import java.util.UUID;

public interface BookView {

    UUID getBookId();

    String getTitle();

    String getDescription();

    Long getPrice();

}
