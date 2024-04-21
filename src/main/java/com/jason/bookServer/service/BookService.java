package com.jason.bookServer.service;

import com.jason.bookServer.data.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private static final Map<String, Book> books = new HashMap<>();

    public BookService() {
        Book book = new Book();
        book.setTitle("西游记");
        book.setAuthor("吴承恩");
        book.setPublicationYear("1592-01-01");
        book.setIsbn(UUID.randomUUID().toString());
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book addBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
        return book;
    }
}
