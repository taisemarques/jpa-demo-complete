package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.PriceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PriceOfferRepository extends JpaRepository<PriceOffer, UUID> {

    @Modifying
    @Query("UPDATE PriceOffer c SET c.active = false WHERE c.book.bookId = ?1")
    void inactivatePriceOfferByBookId(UUID bookId);
}
