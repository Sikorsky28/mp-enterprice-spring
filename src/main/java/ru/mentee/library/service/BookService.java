package ru.mentee.library.service;

import java.util.List;
import java.util.Optional;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;

public interface BookService {
  List<BookDto> getBooks(String author);

  BookDto addBook(CreateBookRequest request);

  Optional<BookDto> getBookById(Long id);
}
