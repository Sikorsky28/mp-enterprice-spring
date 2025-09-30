package ru.mentee.library.service.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.service.BookService;

@Service("inMemoryBookService") // уникальное имя для @Qualifier
public class InMemoryBookService implements BookService {

  private final Map<Long, BookDto> storage = new HashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  public InMemoryBookService() {
    // тестовые данные
    addBook(
        new CreateBookRequest(
            "Преступление и наказание",
            "Фёдор Достоевский",
            "978-5-389-07441-1",
            LocalDate.of(1866, 1, 1),
            true));
    addBook(
        new CreateBookRequest(
            "Изучаем Java", "Кэти Сьерра", "978-5-8459-1831-6", LocalDate.of(2020, 5, 20), true));
  }

  @Override
  public List<BookDto> getBooks(String author) {
    return storage.values().stream()
        .filter(book -> author == null || book.getAuthor().equalsIgnoreCase(author))
        .collect(Collectors.toList());
  }

  @Override
  public BookDto addBook(CreateBookRequest request) {
    Long id = idGenerator.getAndIncrement();
    BookDto book =
        new BookDto(
            id,
            request.getTitle(),
            request.getAuthor(),
            request.getIsbn(),
            request.getPublishedDate(),
            request.getAvailable());
    storage.put(id, book);
    return book;
  }

  @Override
  public Optional<BookDto> getBookById(Long id) {
    return Optional.ofNullable(storage.get(id));
  }
}
