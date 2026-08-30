package com.library.service;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Clean Code", "Robert C. Martin");
        book.setId(1);
    }

    @Test
    void shouldGetAllBooks() {

        List<Book> books = Arrays.asList(
                book,
                new Book("Effective Java", "Joshua Bloch")
        );

        when(repository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());

        verify(repository).findAll();
    }

    @Test
    void shouldGetBookById() {

        when(repository.findById(1))
                .thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1);

        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());

        verify(repository).findById(1);
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {

        when(repository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookById(99)
        );

        verify(repository).findById(99);
    }

    @Test
    void shouldAddBook() {

        when(repository.existsByTitleAndAuthor(
                "Clean Code",
                "Robert C. Martin"
        )).thenReturn(false);

        when(repository.save(book))
                .thenReturn(book);

        Book result = bookService.addBook(book);

        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
        assertEquals("Available", result.getStatus());

        verify(repository).existsByTitleAndAuthor(
                "Clean Code",
                "Robert C. Martin"
        );

        verify(repository).save(book);
    }

    @Test
    void shouldRejectDuplicateBook() {

        when(repository.existsByTitleAndAuthor(
                "Clean Code",
                "Robert C. Martin"
        )).thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> bookService.addBook(book)
        );

        verify(repository, never()).save(book);
    }

    @Test
    void shouldUpdateBook() {

        Book updatedBook =
                new Book("Effective Java", "Joshua Bloch");

        updatedBook.setStatus("Available");

        when(repository.findById(1))
                .thenReturn(Optional.of(book));

        when(repository.save(book))
                .thenReturn(book);

        Book result =
                bookService.updateBook(1, updatedBook);

        assertEquals("Effective Java", result.getTitle());
        assertEquals("Joshua Bloch", result.getAuthor());
        assertEquals("Available", result.getStatus());

        verify(repository).findById(1);
        verify(repository).save(book);
    }

    @Test
    void shouldDeleteBook() {

        when(repository.existsById(1))
                .thenReturn(true);

        boolean result =
                bookService.deleteBook(1);

        assertTrue(result);

        verify(repository).existsById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void shouldReturnFalseWhenDeletingNonExistingBook() {

        when(repository.existsById(99))
                .thenReturn(false);

        boolean result =
                bookService.deleteBook(99);

        assertFalse(result);

        verify(repository).existsById(99);
        verify(repository, never()).deleteById(99);
    }

    @Test
    void shouldIssueBook() {

        when(repository.findById(1))
                .thenReturn(Optional.of(book));

        when(repository.save(book))
                .thenReturn(book);

        Book result =
                bookService.issueBook(1, "Jyothi");

        assertEquals("Issued", result.getStatus());
        assertEquals("Jyothi", result.getBorrowerName());

        assertNotNull(result.getIssueDate());
        assertNotNull(result.getDueDate());
        assertNull(result.getReturnDate());

        verify(repository).findById(1);
        verify(repository).save(book);
    }

    @Test
    void shouldNotIssueAlreadyIssuedBook() {

        book.setStatus("Issued");

        when(repository.findById(1))
                .thenReturn(Optional.of(book));

        assertThrows(
                IllegalStateException.class,
                () -> bookService.issueBook(1, "Jyothi")
        );

        verify(repository, never()).save(book);
    }

    @Test
    void shouldReturnBook() {

        book.setStatus("Issued");
        book.setBorrowerName("Jyothi");

        when(repository.findById(1))
                .thenReturn(Optional.of(book));

        when(repository.save(book))
                .thenReturn(book);

        Book result =
                bookService.returnBook(1);

        assertEquals("Available", result.getStatus());
        assertNotNull(result.getReturnDate());

        verify(repository).findById(1);
        verify(repository).save(book);
    }
}