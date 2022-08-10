package com.example.jpademocomplete.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    @Column(name="author_id", columnDefinition = "uuid")
    private UUID authorId;

    String name;

    @ManyToMany
    @JoinTable(name="book_author",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Book> book;

}
