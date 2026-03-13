package ru.mentee.library.api.dto;

import java.time.LocalDate;

public class CreateBookRequest {
  private String title;
  private String author;
  private String isbn;
  private LocalDate publishedDate;

  // getters и setters

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
}
