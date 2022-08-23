package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.bo.*;
import com.example.jpademocomplete.entities.*;
import com.example.jpademocomplete.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static java.lang.Long.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.example.jpademocomplete.repositories", "com.example.jpademocomplete.services"})
public class BookRepositoryQuerydslTest {

    @Autowired
    BookRepositoryQuerydsl bookRepositoryQuerydsl;

    @Autowired
    BookService bookService;

    @Test
    public void should_find_no_Book_if_repository_is_empty(){
        List<Book> books = bookRepositoryQuerydsl.fetchAll(new BookFilter());
        assertThat(books).isEmpty();
    }

    @Test
    public void should_store_an_Book() {
        BookBO book = BookBO.builder()
                .title("Book1")
                .description("Description")
                .price(valueOf(10))
                .build();
        UUID idBook = bookService.createBook(book);

        AuthorBO author = AuthorBO.builder()
                .name("Mary")
                .build();
        bookService.createBookAuthor(idBook, author);

        ReviewBO review = ReviewBO.builder()
                .comment("Comment")
                .numStars(2)
                .voterName("John")
                .build();
        bookService.createBookReview(idBook, review);

        PriceOfferBO priceOffer = PriceOfferBO.builder()
                .promotionalText("Black Friday")
                .newPrice(valueOf(8))
                .build();
        bookService.createBookPriceOffer(idBook, priceOffer);

        Calendar reservationDate = Calendar.getInstance();
        reservationDate.add(Calendar.DATE, 3);

        ReservationBO reservation = ReservationBO.builder()
                .description("Reservation description")
                .reservationDate(reservationDate.getTime())
                .build();
        bookService.createBookReservation(idBook, reservation);

        BookFilter bookFilter = new BookFilter();
        bookFilter.setAuthorName("Mary");
        List<Book> books = bookRepositoryQuerydsl.fetchAll(bookFilter);
        assertThat(books.size() == 1);
    }
}
