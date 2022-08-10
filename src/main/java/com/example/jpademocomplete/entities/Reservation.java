package com.example.jpademocomplete.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name="reservation_id", columnDefinition = "uuid")
    private UUID reservationId;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name="reservation_creation")
    private Date reservationCreation;

    @Transient
    private int daysToExpire;

    @Temporal(TemporalType.DATE)
    @Column(name="reservation_date")
    private Date reservationDate;

    @OneToOne
    @JoinColumn(name="book_id")
    private Book book;

}
