package com.practica.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.library.model.Book;
import com.practica.library.model.BookDTO;
import com.practica.library.repository.BookRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

import java.util.List;

@Service
public class BookServiceJPA {

    private final BookRepositoryJPA bookRepositoryJPA;
    private final ObjectMapper mapper;

    public BookServiceJPA(BookRepositoryJPA bookRepositoryJPA, ObjectMapper mapper) {
        this.bookRepositoryJPA = bookRepositoryJPA;
        this.mapper = mapper;
    }

    public List<BookDTO> getBooks() {

        return bookRepositoryJPA.findAll()
                .stream()
                .map(book -> mapper.convertValue(book, BookDTO.class))
                .toList();
    }

    public BookDTO saveBook(Book book) {

        return mapper.convertValue(bookRepositoryJPA.save(book), BookDTO.class);
    }
    // test git
    public Optional<Book> getRandomBook() {
        List<Book> books = bookRepositoryJPA.findAll();
        if (books.isEmpty()) {
            return Optional.empty();
        }
        Random rand = new Random();
        Book randomBook = books.get(rand.nextInt(books.size()));
        return Optional.of(randomBook);
    }
}
