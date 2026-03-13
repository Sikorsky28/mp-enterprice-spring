package ru.mentee.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mentee.library.domain.model.ShoppingCart;

@Configuration
public class ApplicationConfig {

  @Bean
  @Scope("prototype")
  public ShoppingCart prototypeBean() {
    return new ShoppingCart();
  }
}
