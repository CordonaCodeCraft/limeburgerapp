package com.limeburger.domain.burger.service.impl;

import com.limeburger.domain.burger.dto.admin.BurgerAdminCommand;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.repository.BurgerRepository;
import com.limeburger.domain.burger.service.BurgerService;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BurgerServiceIml implements BurgerService {

  private final BurgerRepository burgerRepository;
  private final IngredientService ingredientService;

  @Override
  public Burger save(final Burger burger) {
    log.info(String.format("Saved \"%s\" burger in database", burger.getName()));
    return burgerRepository.save(burger);
  }

  @Override
  public Optional<Burger> addNewBurger(final BurgerAdminCommand source) {

    Set<Ingredient> ingredients = getIngredients(source);

    Burger newBurger =
        Burger.builder()
            .burgerType(source.getBurgerType())
            .name(source.getName())
            .description(source.getDescription())
            .imageUrl(source.getImageUrl())
            .profitCoefficient(source.getProfitCoefficient())
            .isInPromotion(source.getIsInPromotion())
            .discountCoefficient(source.getDiscountCoefficient())
            .productionCost(source.getProductionCost())
            .ingredients(new HashSet<>())
            .build();

    newBurger.addIngredients(ingredients);

    Burger saved = burgerRepository.save(newBurger);

    log.info(String.format("Created new \"%s\" burger in database", newBurger.getName()));

    return Optional.ofNullable(saved);
  }

  private Set<Ingredient> getIngredients(final BurgerAdminCommand source) {
    return source.getIngredients().stream()
        .map(n -> ingredientService.getByName(n).get())
        .collect(Collectors.toSet());
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
