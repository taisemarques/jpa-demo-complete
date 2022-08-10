package com.example.jpademocomplete.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class PriceOffer {

    @Id
    @GeneratedValue
    @Column(name="price_offer_id", columnDefinition = "uuid")
    private UUID priceOfferId;

    @Column(name="new_price")
    private Long newPrice;

    @Column(name="promotional_text")
    private String promotionalText;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @Temporal(TemporalType.DATE)
    private Date creation;

    @Column(name="active")
    private Boolean active;

    @Override
    public String toString() {
        return "PriceOffer{" +
                "id=" + priceOfferId +
                ", newPrice=" + newPrice +
                ", promotionalText =" + promotionalText +
                ", active =" + active + "}" ;
    }

}
