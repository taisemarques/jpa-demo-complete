package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
