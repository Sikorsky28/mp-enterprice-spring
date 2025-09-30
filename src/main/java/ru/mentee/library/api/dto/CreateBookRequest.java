package ru.mentee.library.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class CreateBookRequest {
  @NotBlank private String title;

  @NotBlank private String author;

  @NotBlank
  @Pattern(
      regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
      message = "Некорректный ISBN")
  private String isbn;

  private LocalDate publishedDate;
  private Boolean available = true;

  public CreateBookRequest() {}

  public CreateBookRequest(
      String title, String author, String isbn, LocalDate publishedDate, Boolean available) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
    this.available = available;
  }

  // геттеры/сеттеры
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
