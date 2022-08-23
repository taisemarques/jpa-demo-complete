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

    @OneToMany(mappedBy="book", cascade = CascadeType.ALL)
    private List<PriceOffer> priceOffer;

    @OneToMany(mappedBy="book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy="book", cascade = CascadeType.ALL)
    private Reservation reservation;

    @ManyToMany(mappedBy="book", cascade = CascadeType.ALL)
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
