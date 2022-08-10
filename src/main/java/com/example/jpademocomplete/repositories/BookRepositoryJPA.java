package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.Book;
import com.example.jpademocomplete.projections.BookInfoView;
import com.example.jpademocomplete.projections.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookRepositoryJPA extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b LEFT JOIN b.priceOffer pf ON pf.active = true WHERE b.bookId = :idBook")
    BookInfoView findBookInfoViewByBookId(@Param("idBook")UUID idBook);

    @Query("SELECT b FROM Book b LEFT JOIN b.priceOffer pf ON pf.active = true WHERE b.bookId = :idBook")
    BookWithPriceOfferView findByPriceOfferActive(@Param("idBook") UUID idBook);

    BookWithReviewsView findBookWithReviewsViewByBookId(@Param("idBook") UUID idBook);

    //Removing this query will be possible to see the trick behavior called delay loading...
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.reservation r WHERE b.bookId = :idBook")
    BookWithReservations findBookWithReservationsByBookId(@Param("idBook") UUID idBook);

    BookWithAuthorsView findBookWithAuthorsViewByBookId(@Param("idBook") UUID idBook);

}
