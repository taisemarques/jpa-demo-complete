package com.example.jpademocomplete.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue
    @Column(name="review_id", columnDefinition = "uuid")
    private UUID reviewId;

    @Column(name="voter_name")
    private String voterName;

    @Column(name="num_stars")
    private int numStars;

    private String comment;

    @Temporal(TemporalType.DATE)
    private Date creation;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + reviewId +
                ", voterName=" + voterName +
                ", numStars =" + numStars +
                ", comment =" + comment + "}" ;
    }

}
