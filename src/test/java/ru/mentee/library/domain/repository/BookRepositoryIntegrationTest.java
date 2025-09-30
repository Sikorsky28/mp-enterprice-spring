package ru.mentee.library.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.mentee.library.domain.model.Book;

@Testcontainers
@SpringBootTest
class BookRepositoryIntegrationTest {

  @Container
  public static PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:15")
          .withDatabaseName("testdb")
          .withUsername("postgres")
          .withPassword("postgres");

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private BookRepository bookRepository;

  @BeforeEach
  void cleanDatabase() {
    bookRepository.deleteAll(); // очищаем таблицу перед каждым тестом
  }

  @Test
  void testSaveAndFindById() {
    String isbn = "ISBN-" + UUID.randomUUID();
    Book book = new Book(null, "Integration Test", "Author", isbn, true, LocalDate.now());
    Book saved = bookRepository.save(book);

    Book found = bookRepository.findById(saved.getId()).orElseThrow();
    assertEquals(saved.getTitle(), found.getTitle());
    assertEquals(saved.getIsbn(), found.getIsbn());
  }

  @Test
  void testFindAllByAuthor() {
    String isbn1 = "ISBN-" + UUID.randomUUID();
    String isbn2 = "ISBN-" + UUID.randomUUID();
    Book book1 = bookRepository.save(new Book(null, "Book1", "Author1", isbn1, true, LocalDate.now()));
    Book book2 = bookRepository.save(new Book(null, "Book2", "Author1", isbn2, true, LocalDate.now()));

    List<Book> books = bookRepository.findByAuthor("Author1");
    assertEquals(2, books.size());
  }

  @Test
  void testDeleteBook() {
    String isbn = "ISBN-" + UUID.randomUUID();
    Book book = bookRepository.save(new Book(null, "BookToDelete", "Author", isbn, true, LocalDate.now()));
    Long id = book.getId();
    bookRepository.delete(book);
    assertFalse(bookRepository.findById(id).isPresent());
  }

  @Test
  void testUniqueIsbnConstraint() {
    String isbn = "ISBN-" + UUID.randomUUID();
    bookRepository.save(new Book(null, "Book1", "Author", isbn, true, LocalDate.now()));

    assertThrows(Exception.class, () -> {
      bookRepository.save(new Book(null, "Book2", "Author", isbn, true, LocalDate.now()));
    });
  }
}
