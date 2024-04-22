package com.jason.bookServer;

import com.jason.bookServer.data.Book;
import com.jason.bookServer.repository.BookRepository;
import com.jason.bookServer.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setTitle("西游记");
        testBook.setAuthor("吴承恩");
        testBook.setPublicationYear("1592-01-01");
        testBook.setIsbn("123-456-789");
        testBook.setId("1");
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(List.of(testBook));

        // Act & Assert
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(testBook, books.get(0));
    }

    @Test
    void testGetBookById() {
        // Arrange
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(testBook));

        // Act & Assert
        Book book = bookService.getBookById(testBook.getId());
        assertNotNull(book);
        assertEquals(testBook, book);
    }
}