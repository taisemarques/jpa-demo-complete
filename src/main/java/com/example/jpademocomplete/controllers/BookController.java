package com.example.jpademocomplete.controllers;

import com.example.jpademocomplete.bo.*;
import com.example.jpademocomplete.projections.BookInfoView;
import com.example.jpademocomplete.projections.*;
import com.example.jpademocomplete.repositories.BookFilter;
import com.example.jpademocomplete.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/books"})
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<UUID> createBook(@RequestBody BookBO book){
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<List<BookInfoBO>> getBooksByParams(@RequestBody BookFilter bookFilter) {
        return ResponseEntity.ok(bookService.getBookByParams(bookFilter));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<BookInfoView> getBooksById(@PathVariable("id") UUID idBook){
        return ResponseEntity.ok(bookService.getBookById(idBook));
    }

    @PostMapping(value="/{id}/authors")
    public ResponseEntity<UUID> createAuthors(@PathVariable("id") UUID idBook, @RequestBody AuthorBO authorBO){
        return ResponseEntity.ok(bookService.createBookAuthor(idBook, authorBO));
    }

    @GetMapping(value="/{id}/authors")
    public ResponseEntity<BookWithAuthorsView> getBookAuthors(@PathVariable("id") UUID idBook){
        return ResponseEntity.ok(bookService.getAuthorsByBookId(idBook));
    }

    @PostMapping(value="/{id}/priceOffers")
    public ResponseEntity<UUID> createBookPriceOffer(@PathVariable("id") UUID idBook, @RequestBody PriceOfferBO priceOfferBO){
        return ResponseEntity.ok(bookService.createBookPriceOffer(idBook, priceOfferBO));
    }

    @GetMapping(value="/{id}/priceOffers")
    public ResponseEntity<BookWithPriceOfferView> getBookPriceOffers(@PathVariable("id") UUID idBook){
        return ResponseEntity.ok(bookService.getPriceOfferByBookId(idBook));
    }

    @PostMapping(value="/{id}/reviews")
    public ResponseEntity<UUID> createBookReview(@PathVariable("id") UUID idBook, @RequestBody ReviewBO reviewBO){
        return ResponseEntity.ok(bookService.createBookReview(idBook, reviewBO));
    }

    @GetMapping(value="/{id}/reviews")
    public ResponseEntity<BookWithReviewsView> getBookReviews(@PathVariable("id") UUID idBook){
        return ResponseEntity.ok(bookService.getReviewsByBookId(idBook));
    }

    @PostMapping(value="/{id}/reservation")
    public ResponseEntity<UUID> createReservation(@PathVariable("id") UUID idBook, @RequestBody ReservationBO reservationBO){
        return ResponseEntity.ok(bookService.createBookReservation(idBook, reservationBO));
    }

    @GetMapping(value="/{id}/reservation")
    public ResponseEntity<BookWithReservations> getBookReservation(@PathVariable("id") UUID idBook){
        return ResponseEntity.ok(bookService.getReservationByBookId(idBook));
    }

}
