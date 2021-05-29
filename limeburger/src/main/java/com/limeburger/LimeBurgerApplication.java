package com.limeburger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LimeBurgerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LimeBurgerApplication.class, args);
  }
}
