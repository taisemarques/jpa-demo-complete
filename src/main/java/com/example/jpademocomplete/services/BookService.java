package com.example.jpademocomplete.services;

import com.example.jpademocomplete.bo.*;
import com.example.jpademocomplete.entities.*;
import com.example.jpademocomplete.projections.*;
import com.example.jpademocomplete.repositories.BookFilter;
import com.example.jpademocomplete.repositories.BookRepositoryJPA;
import com.example.jpademocomplete.repositories.BookRepositoryQuerydsl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    private BookRepositoryJPA bookRepositoryJPA;
    private BookRepositoryQuerydsl bookRepositoryQuerydsl;
    private PriceOfferService priceOfferService;
    private ReviewService reviewService;
    private ReservationService reservationService;
    private AuthorService authorService;

    public BookService(BookRepositoryJPA bookRepositoryJPA, BookRepositoryQuerydsl bookRepositoryQuerydsl,
                       PriceOfferService priceOfferService, ReviewService reviewService, ReservationService reservationService, AuthorService authorService){
        this.bookRepositoryJPA = bookRepositoryJPA;
        this.bookRepositoryQuerydsl = bookRepositoryQuerydsl;
        this.priceOfferService = priceOfferService;
        this.reviewService = reviewService;
        this.reservationService = reservationService;
        this.authorService = authorService;
    }

    private Converter<BookBO, Book> bookBoToBook = in -> {
        Book book = new Book();
        book.setTitle(in.getTitle());
        book.setDescription(in.getDescription());
        book.setPrice(in.getPrice());
        return book;
    };

    private Converter<Book, BookInfoBO> bookToBookInfoBO = in ->
            new BookInfoBO(in.getTitle(),
                    in.getDescription(),
                    in.getPrice(),
                    authorService.listAuthorToListAuthorBO(in.getAuthors()),
                    priceOfferService.listPriceOfferToListPriceOfferBO(in.getPriceOffer()),
                    reviewService.listReviewToListReview(in.getReviews()),
                    reservationService.reservationToReservationBO.convert(in.getReservation()));

    public UUID createBook(BookBO bookBO) {
        Book bookResponse = bookRepositoryJPA.save(bookBoToBook.convert(bookBO));
        return bookResponse.getBookId();
    }

    public BookInfoView getBookById(UUID idBook) {
        return bookRepositoryJPA.findBookInfoViewByBookId(idBook);
    }

    public UUID createBookPriceOffer(UUID idBook, PriceOfferBO priceOfferBO) {
        //TODO add some treatment in case of idBook invalid
        Book book = bookRepositoryJPA.findById(idBook).get();
        PriceOffer priceOfferResponse = priceOfferService.createPriceOffer(priceOfferBO, book);
        return priceOfferResponse.getPriceOfferId();
    }

    public BookWithPriceOfferView getPriceOfferByBookId(UUID idBook) {
        return bookRepositoryJPA.findByPriceOfferActive(idBook);
    }

    public UUID createBookReview(UUID idBook, ReviewBO reviewBO) {
        //TODO add some treatment in case of idBook invalid
        Book book = bookRepositoryJPA.findById(idBook).get();
        Review reviewResponse = reviewService.createReview(reviewBO, book);
        return reviewResponse.getReviewId();
    }

    public BookWithReviewsView getReviewsByBookId(UUID idBook) {
        return bookRepositoryJPA.findBookWithReviewsViewByBookId(idBook);
    }

    public UUID createBookReservation(UUID idBook, ReservationBO reservationBO) {
        //TODO add some treatment in case of idBook invalid
        Book book = bookRepositoryJPA.findById(idBook).get();
        Reservation reservationResponse = reservationService.createReservation(reservationBO, book);
        return reservationResponse.getReservationId();
    }

    public BookWithReservations getReservationByBookId(UUID idBook) {
        return bookRepositoryJPA.findBookWithReservationsByBookId(idBook);
    }

    public BookWithAuthorsView getAuthorsByBookId(UUID idBook) {
        return bookRepositoryJPA.findBookWithAuthorsViewByBookId(idBook);
    }

    public UUID createBookAuthor(UUID idBook, AuthorBO authorBO) {
        //TODO add some treatment in case of idBook invalid
        Book book = bookRepositoryJPA.findById(idBook).get();
        Author authorResponse = authorService.createAuthor(authorBO, book);
        return authorResponse.getAuthorId();
    }

    public Page<BookInfoBO> getBookByParams(BookFilter bookFilter, Pageable page){
        Page<BookInfoBO> book = listBookToListBookBO(bookRepositoryQuerydsl.fetchAll(bookFilter, page));
        return book;
    }

    private Page<BookInfoBO> listBookToListBookBO(Page<Book> books){
        List<BookInfoBO> bookList = books.stream()
                .map(book -> bookToBookInfoBO.convert(book))
                .collect(toList());
        return new PageImpl(bookList);
    }

}
