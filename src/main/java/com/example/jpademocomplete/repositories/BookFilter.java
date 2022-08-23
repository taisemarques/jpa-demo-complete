package com.example.jpademocomplete.repositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class BookFilter {
    private String authorName = null;
    private Long bookNewPrice = null;
    private String promotionalText = null;
    private String reservationDescription = null;
    private Date reservationDate = null;
    private int numReviewStars = -1;
    private String reviewVoterName = null;
    private String reviewComment = null;
}
