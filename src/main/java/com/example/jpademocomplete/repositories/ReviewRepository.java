package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.Review;
import com.example.jpademocomplete.projections.ReviewView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<ReviewView> findByBook_BookId(UUID bookId);

}
