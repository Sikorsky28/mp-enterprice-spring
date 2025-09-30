package ru.mentee.library.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.domain.model.Book;
import ru.mentee.library.domain.repository.BookRepository;
import ru.mentee.library.service.validation.IsbnValidator;

class BookServiceImplTest {

  private BookRepository bookRepository;
  private BookServiceImpl bookService;

  @BeforeEach
  void setUp() {
    bookRepository = Mockito.mock(BookRepository.class);
    IsbnValidator isbnValidator = new IsbnValidator(); // либо мок через Mockito.mock(IsbnValidator.class)
    bookService = new BookServiceImpl(bookRepository, isbnValidator);
  }

  @Test
  void testAddBook() {
    CreateBookRequest request =
        new CreateBookRequest("Test Title", "Test Author", "123-4567890123", LocalDate.now(), true);

    Book savedBook =
        new Book(
            1L,
            request.getTitle(),
            request.getAuthor(),
            request.getIsbn(),
            request.getAvailable(),
            request.getPublishedDate());

    when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

    BookDto result = bookService.addBook(request);

    assertNotNull(result.getId());
    assertEquals("Test Title", result.getTitle());
    verify(bookRepository, times(1)).save(any(Book.class));
  }

  @Test
  void testGetBookById() {
    Book book = new Book(1L, "Title", "Author", "123-4567890123", true, LocalDate.now());
    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

    Optional<BookDto> dto = bookService.getBookById(1L);
    assertTrue(dto.isPresent());
    assertEquals("Title", dto.get().getTitle());
  }

  @Test
  void testGetBookByIdNotFound() {
    when(bookRepository.findById(999L)).thenReturn(Optional.empty());
    Optional<BookDto> dto = bookService.getBookById(999L);
    assertTrue(dto.isEmpty());
  }

  @Test
  void testGetBooksByAuthor() {
    Book book1 = new Book(1L, "Book1", "Author1", "ISBN-1", true, LocalDate.now());
    Book book2 = new Book(2L, "Book2", "Author1", "ISBN-2", true, LocalDate.now());

    when(bookRepository.findByAuthor("Author1")).thenReturn(Arrays.asList(book1, book2));

    List<BookDto> books = bookService.getBooks("Author1");
    assertEquals(2, books.size());
    assertEquals("Book1", books.get(0).getTitle());
    assertEquals("Book2", books.get(1).getTitle());
  }

  @Test
  void testGetBooksAll() {
    Book book1 = new Book(1L, "Book1", "Author1", "ISBN-1", true, LocalDate.now());
    Book book2 = new Book(2L, "Book2", "Author2", "ISBN-2", true, LocalDate.now());

    when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

    List<BookDto> books = bookService.getBooks(null);
    assertEquals(2, books.size());
  }

  @Test
  void testAddBookInvalidIsbn() {
    CreateBookRequest request =
        new CreateBookRequest("Invalid ISBN Book", "Author", "INVALID-ISBN", LocalDate.now(), true);

    // Предположим, что в сервисе есть проверка ISBN
    assertThrows(IllegalArgumentException.class, () -> bookService.addBook(request));
    verify(bookRepository, never()).save(any());
  }
}
