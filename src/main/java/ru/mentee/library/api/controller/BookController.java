package ru.mentee.library.api.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<BookDto> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
    return bookService
        .getBookById(id)
        .map(ResponseEntity::ok) // если книга найдена → 200 OK
        .orElseGet(() -> ResponseEntity.notFound().build()); // если нет → 404 Not Found
  }

  @PostMapping
  public ResponseEntity<BookDto> createBook(@RequestBody CreateBookRequest request) {

    BookDto book = bookService.createBook(request);

    URI location = URI.create("/api/books/" + book.getId());

    return ResponseEntity.created(location).body(book);
  }
}
