package com.example.jpademocomplete.repositories;

import lombok.Value;
import java.util.Date;

@Value
public class BookFilter {
    String authorName;
    Long bookNewPrice;
    String promotionalText;
    String reservationDescription;
    Date reservationDate;
    int numReviewStars;
    String reviewVoterName;
    String reviewComment;
}
