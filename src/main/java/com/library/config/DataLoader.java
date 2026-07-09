package com.library.config;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(BookRepository repository) {
        return args -> {

            repository.save(new Book(1, "Clean Code", "Robert C. Martin"));
            repository.save(new Book(2, "Effective Java", "Joshua Bloch"));

        };
    }
}