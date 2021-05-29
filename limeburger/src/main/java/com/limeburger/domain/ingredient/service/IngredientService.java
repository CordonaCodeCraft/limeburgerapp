package com.limeburger.domain.ingredient.service;

import com.limeburger.domain.ingredient.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

  Ingredient save(final Ingredient ingredient);

  Ingredient getById(final Long id);

  Optional<Ingredient> getByName(final String name);

  List<Ingredient> saveAll(final List<Ingredient> ingredients);
}
