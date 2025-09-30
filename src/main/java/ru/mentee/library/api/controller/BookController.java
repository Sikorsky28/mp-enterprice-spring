package ru.mentee.library.api.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  // выбираем нужную реализацию через @Qualifier
  public BookController(@Qualifier("bookServiceImpl") BookService bookService) {
    this.bookService = bookService;
  }

  // Получить список книг (с фильтрацией по автору)
  @GetMapping
  public List<BookDto> getBooks(@RequestParam(required = false) String author) {
    return bookService.getBooks(author);
  }

  // Добавить новую книгу
  @PostMapping
  public ResponseEntity<BookDto> addBook(@Valid @RequestBody CreateBookRequest request) {
    BookDto createdBook = bookService.addBook(request);

    // возвращаем 201 Created и заголовок Location: /api/books/{id}
    return ResponseEntity.created(URI.create("/api/books/" + createdBook.getId()))
        .body(createdBook);
  }

  // Получить книгу по ID
  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getById(@PathVariable Long id) {
    return bookService
        .getBookById(id)
        .map(ResponseEntity::ok) // 200 OK + JSON
        .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found
  }
}
