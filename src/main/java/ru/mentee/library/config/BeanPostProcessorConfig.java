package ru.mentee.library.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorConfig implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    if (bean.getClass().getPackageName().startsWith("ru.mentee.library")) {
      System.out.println("До инициализации кастомного бина: " + beanName);
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    if (bean.getClass().getPackageName().startsWith("ru.mentee.library")) {
      System.out.println("После инициализации кастомного бина: " + beanName);
    }
    return bean;
  }
}
