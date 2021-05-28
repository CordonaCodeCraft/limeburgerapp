package com.limeburger.domain.burger.service.impl;

import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.repository.BurgerRepository;
import com.limeburger.domain.burger.service.BurgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
  public Page<Burger> findAllBurgers(final Pageable pageable) {
    return burgerRepository.findAllBurgers(pageable);
  }

  @Override
  public Optional<Burger> findById(final Long id) {
    return burgerRepository.findBurgerById(id);
  }

  @Override
  public Optional<Burger> findByName(final String name) {
    return burgerRepository.findBurgerByName(name);
  }

  @Override
  public Optional<Burger> findByNameLike(final String name) {
    return burgerRepository.findBurgerByNameLike(name);
  }

  @Override
  public List<Burger> saveAll(final List<Burger> burgers) {
    burgers.forEach(b -> log.info(String.format("Saved \"%s\" burger in database", b.getName())));
    log.info(String.format("Saved a total of %d burgers in database", burgers.size()));
    return burgerRepository.saveAll(burgers);
  }

  public Burger getRandomBurger() {
    final List<Long> allIds = burgerRepository.getAllIds();
    final Random random = new Random();
    final int randomIndex = random.ints(0, allIds.size()).findFirst().getAsInt();
    return burgerRepository.getById(allIds.get(randomIndex));
  }
}
