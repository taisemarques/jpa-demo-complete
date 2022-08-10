package com.example.jpademocomplete.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    @Column(name="book_id", columnDefinition = "uuid")
    private UUID bookId;

    private String title;

    private String description;

    private Long price;

    @OneToMany(mappedBy="book")
    private List<PriceOffer> priceOffer;

    @OneToMany(mappedBy="book")
    private List<Review> reviews;

    @OneToOne(mappedBy="book")
    private Reservation reservation;

    @ManyToMany(mappedBy="book")
    private List<Author> authors;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title=" + title +
                ", description =" + description +
                ", price =" + price + "}" ;
    }

}
