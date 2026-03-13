package ru.mentee.library.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
class BookControllerIntegrationTest {

  @Container static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
    registry.add(
        "spring.liquibase.change-log", () -> "classpath:db/changelog/db.changelog-master.yaml");
  }

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  @Test
  @DisplayName("Should создать книгу с валидными данными")
  void shouldCreateBookWithValidData() throws Exception {
    // Given
    String requestJson =
        """
        {"title": "Test Book", "author": "Test Author", "isbn": "978-3-16-148410-0"}
    """;

    // Создаём DTO, который вернёт сервис (как в реальном ответе API)
    BookDto savedBookDto = new BookDto();
    savedBookDto.setId(1L);
    savedBookDto.setTitle("Test Book");
    savedBookDto.setAuthor("Test Author");
    savedBookDto.setIsbn("978-3-16-148410-0");
    // ... заполни остальные поля, если нужно (publishedDate, available и т.д.)

    when(bookService.createBook(any(CreateBookRequest.class))).thenReturn(savedBookDto);

    // When & Then
    mockMvc
        .perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(requestJson))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"));
  }
}
