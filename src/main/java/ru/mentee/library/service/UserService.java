package ru.mentee.library.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final NotificationService notificationService;

  public UserService(@Qualifier("smsService") NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostConstruct
  public void init() {
    System.out.println("UserService инициализирован");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("UserService уничтожается");
  }

  public void registerUser(String name) {
    notificationService.notify("Пользователь " + name + " зарегистрирован");
  }
}
