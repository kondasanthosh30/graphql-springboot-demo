package com.test.graphql.service;

import com.test.graphql.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService {

    List<Book> bookList= new ArrayList<>();

    AtomicInteger atomicInteger = new AtomicInteger(0);


    public List<Book> findAll() {
        return bookList;
    }

    public Optional<Book> findOne(Integer id) {
        return bookList.stream().
                filter(book -> book.id()== id).findFirst();
    }

    public Book create(String bookName, String zooner) {
        Book b = new Book(atomicInteger.incrementAndGet(),bookName,zooner);
        bookList.add(b);
        return b;
    }

    public Book delete(Integer id) {
        Book b = bookList.stream().filter(book -> book.id()==id).findFirst().orElseThrow(() -> new IllegalArgumentException());
        bookList.remove(b);
        return b;
    }

    public Book update(Integer id,String bookName,String zooner) {
        Book updatedBook = new Book(id,bookName,zooner);
        Optional<Book> b = bookList.stream().filter(book -> book.id()==id).findFirst();
        if (b.isPresent()) {
            Book existing = b.get();
            int index= bookList.indexOf(existing);
            bookList.set(index,updatedBook);
        } else  {
            throw new IllegalArgumentException("Invalid");
        }
        return updatedBook;
    }

    @PostConstruct
    public void init() {
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book1","Sports"));
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book2","Love"));
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book3","Technology"));
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book4","Science"));
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book5","Horror"));
        bookList.add(new Book(atomicInteger.incrementAndGet(),"Book6","Medical"));
    }
}

