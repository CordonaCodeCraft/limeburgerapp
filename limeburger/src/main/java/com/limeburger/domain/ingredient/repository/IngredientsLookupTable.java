package com.limeburger.domain.ingredient.repository;

import com.limeburger.domain.ingredient.model.Ingredient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public abstract class IngredientsLookupTable {

  private static final Map<String, Ingredient> ingredientsTable = new HashMap<>();

  public static void initializeWith(List<Ingredient> ingredients) {
    ingredients.forEach(i -> ingredientsTable.putIfAbsent(i.getName(), i));
    log.info(
        String.format(
            "Ingredients lookup table initialized with %d ingredients", ingredientsTable.size()));
  }

  public static Map<String, Ingredient> getTable() {
    return ingredientsTable;
  }
}
