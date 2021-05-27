package com.limeburger.domain.ingredient.service;

import com.limeburger.domain.ingredient.model.Ingredient;

import java.util.List;

public interface IngredientService {

  Ingredient save(Ingredient ingredient);

  Ingredient getById(Long id);

  List<Ingredient> saveAll(List<Ingredient> ingredients);
}
