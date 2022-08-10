package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.*;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import static java.util.Objects.nonNull;

@Component
public class BookRepositoryQuerydsl extends QuerydslRepositorySupport {

    public BookRepositoryQuerydsl() {
        super(Book.class);
    }

    public Page<Book> fetchAll(BookFilter filterParams, Pageable pageable) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QPriceOffer priceOffer = QPriceOffer.priceOffer;
        QReservation reservation = QReservation.reservation;
        QReview review = QReview.review;

        JPQLQuery<Book> query = from(QBook.book)
                .leftJoin(reservation).on(book.reservation.reservationId.eq(reservation.reservationId))
                .leftJoin(author).on(book.authors.contains(author))
                .leftJoin(priceOffer).on(book.priceOffer.contains(priceOffer))
                .leftJoin(review).on(book.reviews.contains(review));

        if(nonNull(filterParams.getAuthorName()))
            query = query.where(author.name.likeIgnoreCase(filterParams.getAuthorName()));

        if(nonNull(filterParams.getBookNewPrice()))
            query = query.where(priceOffer.newPrice.eq(filterParams.getBookNewPrice()));

        if(nonNull(filterParams.getPromotionalText()))
            query = query.where(priceOffer.promotionalText.likeIgnoreCase(filterParams.getPromotionalText()));

        if(nonNull(filterParams.getReservationDescription()))
            query = query.where(reservation.description.likeIgnoreCase(filterParams.getReservationDescription()));

        if(nonNull(filterParams.getReservationDate()))
            query = query.where(reservation.reservationDate.eq(filterParams.getReservationDate()));

        if(nonNull(filterParams.getNumReviewStars()) && filterParams.getNumReviewStars() != 0)
            query = query.where(review.numStars.eq(filterParams.getNumReviewStars()));

        if(nonNull(filterParams.getReviewVoterName()))
            query = query.where(review.voterName.likeIgnoreCase(filterParams.getReviewVoterName()));

        if(nonNull(filterParams.getReviewComment()))
            query = query.where(review.comment.likeIgnoreCase(filterParams.getReviewComment()));

        return new PageImpl(query.orderBy(book.bookId.asc()).fetch(), pageable, 15);
    }
}
