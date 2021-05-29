package com.limeburger.domain.ingredient.service;

import com.limeburger.domain.ingredient.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

  Ingredient save(Ingredient ingredient);

  Ingredient getById(Long id);

  Optional<Ingredient> getByName(String name);

  List<Ingredient> saveAll(List<Ingredient> ingredients);
}
