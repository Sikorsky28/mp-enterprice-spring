package ru.mentee.library.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String title;

  @NotNull
  @Column(nullable = false)
  private String author;

  @NotNull
  @Column(nullable = false, unique = true)
  private String isbn;

  private LocalDate publishedDate;
  private Boolean available = true;

  // 🔹 Конструктор без аргументов (обязателен для JPA)
  public Book() {}

  // 🔹 Удобный конструктор с параметрами (для создания вручную)
  public Book(
      Long id,
      String title,
      String author,
      String isbn,
      Boolean available,
      LocalDate publishedDate) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.available = available;
    this.publishedDate = publishedDate;
  }

  // геттеры/сеттеры
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getIsbn() {
    return isbn;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return available == book.available
        && Objects.equals(id, book.id)
        && Objects.equals(title, book.title)
        && Objects.equals(author, book.author)
        && Objects.equals(isbn, book.isbn)
        && Objects.equals(publishedDate, book.publishedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, isbn, publishedDate, available);
  }

  @Override
  public String toString() {
    return "Book{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", isbn='"
        + isbn
        + '\''
        + ", publishedDate="
        + publishedDate
        + ", available="
        + available
        + '}';
  }
}
