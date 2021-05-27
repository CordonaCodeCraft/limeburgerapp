package com.limeburger.domain.ingredient.service.impl;

import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.ingredient.repository.IngredientRepository;
import com.limeburger.domain.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientServiceImpl implements IngredientService {

  private final IngredientRepository ingredientRepository;

  @Override
  public Ingredient save(final Ingredient ingredient) {
    log.info(String.format("Saved \"%s\" ingredient in database", ingredient.getName()));
    return ingredientRepository.save(ingredient);
  }

  @Override
  public Ingredient getById(final Long id) {
    return ingredientRepository.getIngredientById(id);
  }

  @Override
  public List<Ingredient> saveAll(final List<Ingredient> ingredients) {
    ingredients.forEach(
        i -> log.info(String.format("Saved \"%s\" ingredient in database", i.getName())));
    log.info(String.format("Saved a total of %d ingredients in database", ingredients.size()));
    return ingredientRepository.saveAll(ingredients);
  }
}
