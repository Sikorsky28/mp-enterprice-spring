package ru.mentee.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mentee.library.domain.repository.BookRepository;
import ru.mentee.library.service.BookService;
import ru.mentee.library.service.impl.BookServiceImpl;
import ru.mentee.library.service.validation.IsbnValidator;

@Configuration
public class ApplicationConfig {

  @Bean
  public BookService bookService(BookRepository repository, IsbnValidator validator) {
    return new BookServiceImpl(repository, validator);
  }
}