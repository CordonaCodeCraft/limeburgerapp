package com.limeburger.domain.ingredient.service;

import com.limeburger.domain.ingredient.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

  Ingredient save(final Ingredient ingredient);

  Ingredient getById(final Long id);

  Optional<Ingredient> getByName(final String name);

  List<Ingredient> saveAll(final List<Ingredient> ingredients);
}
