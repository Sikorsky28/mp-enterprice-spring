package ru.mentee.library.service.impl;

import org.springframework.stereotype.Component;
import ru.mentee.library.service.NotificationService;

@Component("emailService")
class EmailNotificationService implements NotificationService {
  @Override
  public void notify(String message) {
    System.out.println("Email: " + message);
  }
}
