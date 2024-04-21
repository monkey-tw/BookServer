package com.jason.bookServer;
import com.jason.bookServer.controller.BookController;
import com.jason.bookServer.data.Book;
import com.jason.bookServer.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBooks() throws Exception {
        // Arrange
        Book book1 = new Book();
        book1.setId("1");
        book1.setTitle("Book One");

        Book book2 = new Book();
        book2.setId("2");
        book2.setTitle("Book Two");

        when(bookService.getAllBooks()).thenReturn(List.of(book1, book2));

        // Act & Assert
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[1].title").value("Book Two"));
    }

    @Test
    void testAddBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setId("id 1");
        book.setTitle("New Book");
        book.setAuthor("author 1");
        book.setPublicationYear("2020-01-01");
        book.setIsbn("123");

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        // Act & Assert
        String content = "{ \"id\": \"id 1\", \"title\": \"New Book\", \"author\": \"author 1\", \"publicationYear\": \"2020-01-01\", \"isbn\": \"123\" }";
        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content("{ \"title\": \"New Book\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"));
    }
}
