package ru.mentee.library.api.dto;

public class BookDto {
  private String title;
  private String author;
  private String isbn;
  private Long id;

  public BookDto(String title, String author, String isbn, Long id) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.id = id;
  }

  public BookDto() {}

  // getters и setters

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

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
