package ru.mentee.library.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String author;

  @Column(nullable = false, length = 200)
  private String title;

  @Column(nullable = false, unique = true)
  private String isbn;

  private LocalDate publishedDate;

  private Boolean available;

  public Book() {}

  public Book(
      Long id,
      String author,
      String title,
      String isbn,
      LocalDate publishedDate,
      Boolean available) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
    this.available = available;
  }

  public Long getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
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

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setTitle(String title) {
    this.title = title;
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
}
