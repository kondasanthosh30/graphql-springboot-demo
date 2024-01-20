package com.test.graphql.controller;

import com.test.graphql.model.Book;
import com.test.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @QueryMapping
    public Optional<Book> findOne(@Argument  Integer id) {
        return bookService.findOne(id);
    }

    @MutationMapping
    public Book create(@Argument String bookName, @Argument String zoner) {
        return bookService.create(bookName,zoner);
    }
}
