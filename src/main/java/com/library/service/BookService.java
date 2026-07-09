package com.library.service;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(int id, Book updatedBook) {

        Book existingBook = repository.findById(id).orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());

            return repository.save(existingBook);
        }

        return null;
    }

    public boolean deleteBook(int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}