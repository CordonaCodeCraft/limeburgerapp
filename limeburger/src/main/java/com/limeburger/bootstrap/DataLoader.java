package com.limeburger.bootstrap;

import com.limeburger.bootstrap.initializers.AllergensInitializer;
import com.limeburger.bootstrap.initializers.BurgerInitializer;
import com.limeburger.bootstrap.initializers.IngredientsInitializer;
import com.limeburger.domain.allergen.entity.Allergen;
import com.limeburger.domain.allergen.service.AllergenService;
import com.limeburger.domain.burger.service.BurgerService;
import com.limeburger.domain.ingredient.entity.Ingredient;
import com.limeburger.domain.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

  private final AllergenService allergenService;
  private final IngredientService ingredientService;
  private final BurgerService burgerService;

  @Override
  public void run(final ApplicationArguments args) {

    final List<Allergen> allergens = AllergensInitializer.initializeAllergens(allergenService);

    final List<Ingredient> ingredients =
        IngredientsInitializer.initializeIngredients(ingredientService, allergenService, allergens);

    BurgerInitializer.initializeBurgers(burgerService, ingredientService, ingredients);
  }
}
