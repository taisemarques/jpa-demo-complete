package com.example.jpademocomplete.repositories;

import com.example.jpademocomplete.entities.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void should_find_no_Author_if_repository_is_empty(){
        List<Author> authors = authorRepository.findAll();
        assertThat(authors).isEmpty();
    }

    @Test
    public void should_store_an_Author() {
        Author author = new Author();
        author.setName("Arthur");
        Author authorSaved = authorRepository.save(author);
        assertThat(authorSaved.getAuthorId()).isNotNull();
        assertThat(authorSaved).hasFieldOrPropertyWithValue("name", author.getName());
    }
}
