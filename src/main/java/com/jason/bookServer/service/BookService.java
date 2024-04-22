package com.jason.bookServer.service;

import com.jason.bookServer.data.Book;
import com.jason.bookServer.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService() {}

    @PostConstruct
    private void initData(){
        Book book = new Book();
        book.setTitle("西游记");
        book.setAuthor("吴承恩");
        book.setPublicationYear("1592-01-01");
        book.setIsbn(UUID.randomUUID().toString());
        book.setId(UUID.randomUUID().toString());
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book deleteBook(String id) {
        Book book = getBookById(id);
        bookRepository.deleteById(id);
        return book;
    }

    public Book updateBook(String id, Book updatedBook) {
        // 首先检查书籍是否存在
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            // 更新书籍的属性，不包括id
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            // 保存更改
            return bookRepository.save(book);
        } else {
            // 如果书籍不存在，则抛出异常
            throw new RuntimeException("Book not found for the id: " + id);
        }
    }
}
