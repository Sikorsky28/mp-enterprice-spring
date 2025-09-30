package ru.mentee.library.service.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IsbnValidator {

  // Регулярное выражение для ISBN-10 и ISBN-13 (с дефисами или без)
  private static final Pattern ISBN_PATTERN = Pattern.compile(
      "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$"
  );

  /**
   * Проверяет, что ISBN соответствует формату ISBN-10 или ISBN-13.
   *
   * @param isbn строка ISBN
   * @return true если ISBN валиден, иначе false
   */
  public boolean isValid(String isbn) {
    if (isbn == null || isbn.isBlank()) {
      return false;
    }
    return ISBN_PATTERN.matcher(isbn).matches();
  }
}
