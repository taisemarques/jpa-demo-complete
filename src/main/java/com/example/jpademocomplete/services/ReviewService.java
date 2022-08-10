package com.example.jpademocomplete.services;

import com.example.jpademocomplete.bo.AuthorBO;
import com.example.jpademocomplete.bo.ReviewBO;
import com.example.jpademocomplete.entities.Author;
import com.example.jpademocomplete.entities.Book;
import com.example.jpademocomplete.entities.Review;
import com.example.jpademocomplete.repositories.ReviewRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    private Converter<ReviewBO, Review> reviewBOToReview = in -> {
        Review review = new Review();
        review.setVoterName(in.getVoterName());
        review.setComment(in.getComment());
        review.setNumStars(in.getNumStars());
        return review;
    };

    private Converter<Review, ReviewBO> reviewToReviewBO = in -> {
        ReviewBO reviewBO = new ReviewBO();
        reviewBO.setComment(in.getComment());
        reviewBO.setNumStars(in.getNumStars());
        reviewBO.setVoterName(in.getVoterName());
        return reviewBO;
    };

    ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(ReviewBO reviewBO, Book book) {
        Review review = reviewBOToReview.convert(reviewBO);
        review.setCreation(new Date());
        review.setBook(book);
        return reviewRepository.save(review);
    }

    public List<ReviewBO> listReviewToListReview(List<Review> reviews) {
        return reviews.stream()
                .map(review -> reviewToReviewBO.convert(review))
                .collect(Collectors.toList());
    }
}
