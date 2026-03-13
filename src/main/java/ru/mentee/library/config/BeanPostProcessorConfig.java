package ru.mentee.library.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPostProcessorConfig {

  @Bean
  public BeanPostProcessor loggingBeanPostProcessor() {
    return new BeanPostProcessor() {
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName)
          throws BeansException {
        System.out.println("Создание бина (до init): " + beanName);
        return bean;
      }

      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName)
          throws BeansException {
        System.out.println("Создание бина (после init): " + beanName);
        return bean;
      }
    };
  }
}
