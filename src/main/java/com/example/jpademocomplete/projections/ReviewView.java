package com.example.jpademocomplete.projections;

import java.util.Date;
import java.util.UUID;

public interface ReviewView {

    UUID getReviewId();

    String getVoterName();

    int getNumStars();

    String getComment();

    Date getCreation();

}
