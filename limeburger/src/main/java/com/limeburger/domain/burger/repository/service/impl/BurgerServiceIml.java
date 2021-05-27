package com.limeburger.domain.burger.repository.service.impl;

import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.repository.BurgerRepository;
import com.limeburger.domain.burger.repository.service.BurgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BurgerServiceIml implements BurgerService {

  private final BurgerRepository burgerRepository;

  @Override
  public Burger save(final Burger burger) {
    log.info(String.format("Saved \"%s\" burger in database", burger.getName()));
    return burgerRepository.save(burger);
  }

  @Override
  public List<Burger> saveAll(final List<Burger> burgers) {
    burgers.forEach(b -> log.info(String.format("Saved \"%s\" burger in database", b.getName())));
    log.info(String.format("Saved a total of %d burgers in database", burgers.size()));
    return burgerRepository.saveAll(burgers);
  }
}
