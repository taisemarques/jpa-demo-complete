package com.example.jpademocomplete.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationBO {

    private String description;

    private Date reservationDate;

}
