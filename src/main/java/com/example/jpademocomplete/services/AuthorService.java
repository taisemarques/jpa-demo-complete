package com.example.jpademocomplete.services;

import com.example.jpademocomplete.bo.AuthorBO;
import com.example.jpademocomplete.entities.Author;
import com.example.jpademocomplete.entities.Book;
import com.example.jpademocomplete.repositories.AuthorRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    private Converter<AuthorBO, Author> authorBOToAuthor = in -> {
        Author author = new Author();
        author.setName(in.getName());
        return author;
    };

    private Converter<Author, AuthorBO> authorToAuthorBO = in -> {
        AuthorBO authorBO = AuthorBO.builder()
                .name(in.getName())
                .build();
        return authorBO;
    };

    AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(AuthorBO authorBO, Book book) {
        Author author = authorBOToAuthor.convert(authorBO);
        author.setBook(Arrays.asList(book));
        return authorRepository.save(author);
    }

    public List<AuthorBO> listAuthorToListAuthorBO(List<Author> authors){
        return authors.stream()
                .map(author -> authorToAuthorBO.convert(author))
                .collect(Collectors.toList());
    }


}
