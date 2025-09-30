package ru.mentee.library.api.dto;

import java.time.LocalDate;

public class BookDto {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private LocalDate publishedDate;
  private Boolean available;

  public BookDto() {}

  public BookDto(
      Long id,
      String title,
      String author,
      String isbn,
      LocalDate publishedDate,
      Boolean available) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
    this.available = available;
  }

  // геттеры/сеттеры
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }
}
