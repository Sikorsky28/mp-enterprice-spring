package ru.mentee.library.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.domain.model.Book;
import ru.mentee.library.domain.repository.BookRepository;
import ru.mentee.library.service.BookService;
import ru.mentee.library.service.validation.IsbnValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final IsbnValidator isbnValidator;

  public BookServiceImpl(BookRepository bookRepository, IsbnValidator isbnValidator) {
    this.bookRepository = bookRepository;
    this.isbnValidator = isbnValidator;
  }

  @Override
  public BookDto addBook(CreateBookRequest request) {
    // Автоматическая проверка ISBN
    if (!isbnValidator.isValid(request.getIsbn())) {
      throw new IllegalArgumentException("Некорректный ISBN: " + request.getIsbn());
    }

    Book book = new Book(
        null,
        request.getTitle(),
        request.getAuthor(),
        request.getIsbn(),
        request.getAvailable(),
        request.getPublishedDate()
    );

    Book savedBook = bookRepository.save(book);

    return mapToDto(savedBook);
  }

  @Override
  public Optional<BookDto> getBookById(Long id) {
    return bookRepository.findById(id).map(this::mapToDto);
  }

  @Override
  public List<BookDto> getBooks(String author) {
    List<Book> books;
    if (author != null && !author.isBlank()) {
      books = bookRepository.findByAuthor(author);
    } else {
      books = bookRepository.findAll();
    }
    return books.stream().map(this::mapToDto).collect(Collectors.toList());
  }

  private BookDto mapToDto(Book book) {
    return new BookDto(
        book.getId(),
        book.getTitle(),
        book.getAuthor(),
        book.getIsbn(),
        book.getPublishedDate(),
        book.getAvailable()
    );
  }
  @PostConstruct
  public void init() {
    System.out.println("BookService инициализирован");
  }

  @PreDestroy
  public void cleanup() {
    System.out.println("BookService уничтожается");
  }
}
