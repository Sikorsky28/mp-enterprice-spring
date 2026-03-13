package ru.mentee.library.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mentee.library.api.dto.BookDto;
import ru.mentee.library.api.dto.CreateBookRequest;
import ru.mentee.library.domain.model.Book;
import ru.mentee.library.domain.repository.BookRepository;
import ru.mentee.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<BookDto> getAllBooks() {
    return repository.findAll().stream()
        .map(book -> new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getId()))
        .toList();
  }

  @Override
  public BookDto createBook(CreateBookRequest request) {
    Book book = new Book();
    book.setTitle(request.getTitle());
    book.setAuthor(request.getAuthor());
    book.setIsbn(request.getIsbn());
    book.setPublishedDate(request.getPublishedDate());
    book.setAvailable(true);

    Book saved = repository.save(book);
    return new BookDto(saved.getTitle(), saved.getAuthor(), saved.getIsbn(), saved.getId());
  }

  @Override
  public Optional<BookDto> getBookById(Long id) {
    return repository
        .findById(id)
        .map(book -> new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getId()));
  }
}
