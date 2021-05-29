package com.limeburger.bootstrap.initializers;

import com.limeburger.domain.allergen.model.Allergen;
import com.limeburger.domain.allergen.service.AllergenService;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.ingredient.repository.IngredientsLookupTable;
import com.limeburger.domain.ingredient.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.limeburger.domain.ingredient.model.Ingredient.IngredientType.*;

@Slf4j
@Component
public class IngredientsInitializer extends Initializer {

  private static final String MEAT_IMAGES_PATH = IMAGES_PATH + "ingredients/meats/";
  private static final String BREAD_IMAGES_PATH = IMAGES_PATH + "ingredients/breads/";
  private static final String SALAD_IMAGES_PATH = IMAGES_PATH + "ingredients/salads/";
  private static final String SAUCE_IMAGES_PATH = IMAGES_PATH + "ingredients/sauces/";

  public static List<Ingredient> initializeIngredients(
      final IngredientService ingredientService,
      final AllergenService allergenService,
      final List<Allergen> allergens) {

    log.info(">>>>>>>>>> INITIALIZING INGREDIENTS... <<<<<<<<< ");

    final Ingredient chickenMeat =
        Ingredient.builder()
            .ingredientType(MEAT)
            .name("Chicken meat")
            .description("Tasty chicken meat")
            .imageUrl(MEAT_IMAGES_PATH + "chickenMeat.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(100.25))
            .sellingPrice(BigDecimal.valueOf(2.00))
            .purchasePrice(BigDecimal.valueOf(1.00))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedChickenMeat =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                chickenMeat, getRandomObjects(allergens), allergenService));

    final Ingredient fishMeat =
        Ingredient.builder()
            .ingredientType(MEAT)
            .name("Fish meat")
            .description("Crispy fish meat")
            .imageUrl(MEAT_IMAGES_PATH + "fishMeat.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(85.96))
            .sellingPrice(BigDecimal.valueOf(1.50))
            .purchasePrice(BigDecimal.valueOf(0.50))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedFishMeat =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                fishMeat, getRandomObjects(allergens), allergenService));

    final Ingredient porkMeat =
        Ingredient.builder()
            .ingredientType(MEAT)
            .name("Pork meat")
            .description("Yummy yummy pork meat")
            .imageUrl(MEAT_IMAGES_PATH + "porkMeat.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(120.89))
            .sellingPrice(BigDecimal.valueOf(2.50))
            .purchasePrice(BigDecimal.valueOf(1.50))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedPorkMeat =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                porkMeat, getRandomObjects(allergens), allergenService));

    final Ingredient whiteBread =
        Ingredient.builder()
            .ingredientType(BREAD)
            .name("White bread")
            .description("Gentle white bread")
            .imageUrl(BREAD_IMAGES_PATH + "whiteBread.jpg")
            .grammage(80)
            .calories(BigDecimal.valueOf(90.21))
            .sellingPrice(BigDecimal.valueOf(1.00))
            .purchasePrice(BigDecimal.valueOf(0.75))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedWhiteBread =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                whiteBread, getRandomObjects(allergens), allergenService));

    final Ingredient brownBread =
        Ingredient.builder()
            .ingredientType(BREAD)
            .name("Brown bread")
            .description("Soft brown bread")
            .imageUrl(BREAD_IMAGES_PATH + "brownBread.jpg")
            .grammage(80)
            .calories(BigDecimal.valueOf(40.22))
            .sellingPrice(BigDecimal.valueOf(1.50))
            .purchasePrice(BigDecimal.valueOf(0.60))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedBrownBread =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                brownBread, getRandomObjects(allergens), allergenService));

    final Ingredient sesameBread =
        Ingredient.builder()
            .ingredientType(BREAD)
            .name("Sesame bread")
            .description("Exotic sesame bread")
            .imageUrl(BREAD_IMAGES_PATH + "sesameBread.jpg")
            .grammage(80)
            .calories(BigDecimal.valueOf(90.92))
            .sellingPrice(BigDecimal.valueOf(2.50))
            .purchasePrice(BigDecimal.valueOf(0.65))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedSesameBread =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                sesameBread, getRandomObjects(allergens), allergenService));

    final Ingredient greenSalad =
        Ingredient.builder()
            .ingredientType(SALAD)
            .name("Green salad")
            .description("Healthy green salad")
            .imageUrl(SALAD_IMAGES_PATH + "greenSalad.jpg")
            .grammage(50)
            .calories(BigDecimal.valueOf(25.25))
            .sellingPrice(BigDecimal.valueOf(1.50))
            .purchasePrice(BigDecimal.valueOf(0.65))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedGreenSalad =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                greenSalad, getRandomObjects(allergens), allergenService));

    final Ingredient onionSalad =
        Ingredient.builder()
            .ingredientType(SALAD)
            .name("Onion salad")
            .description("Stinky onion salad")
            .imageUrl(SALAD_IMAGES_PATH + "onionSalad.jpg")
            .grammage(50)
            .calories(BigDecimal.valueOf(10.25))
            .sellingPrice(BigDecimal.valueOf(3.50))
            .purchasePrice(BigDecimal.valueOf(0.55))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedOnionSalad =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                onionSalad, getRandomObjects(allergens), allergenService));

    final Ingredient russianSalad =
        Ingredient.builder()
            .ingredientType(SALAD)
            .name("Russian salad")
            .description("Calorie bomb russian salad")
            .imageUrl(SALAD_IMAGES_PATH + "russianSalad.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(249.23))
            .sellingPrice(BigDecimal.valueOf(2.50))
            .purchasePrice(BigDecimal.valueOf(0.85))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedRussianSalad =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                russianSalad, getRandomObjects(allergens), allergenService));

    final Ingredient barbequeSauce =
        Ingredient.builder()
            .ingredientType(SAUCE)
            .name("Barbeque sauce")
            .description("Tasty barbeque sauce")
            .imageUrl(SAUCE_IMAGES_PATH + "barbequeSauce.jpg")
            .grammage(10)
            .calories(BigDecimal.valueOf(18.24))
            .sellingPrice(BigDecimal.valueOf(1.00))
            .purchasePrice(BigDecimal.valueOf(0.50))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedBarbequeSauce =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                barbequeSauce, getRandomObjects(allergens), allergenService));

    final Ingredient ketchupSauce =
        Ingredient.builder()
            .ingredientType(SAUCE)
            .name("Ketchup sauce")
            .description("Bloody ketchup sauce")
            .imageUrl(SAUCE_IMAGES_PATH + "ketchupSauce.jpg")
            .grammage(10)
            .calories(BigDecimal.valueOf(10.84))
            .sellingPrice(BigDecimal.valueOf(1.00))
            .purchasePrice(BigDecimal.valueOf(0.60))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedKetchupSauce =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                ketchupSauce, getRandomObjects(allergens), allergenService));

    final Ingredient mustardSauce =
        Ingredient.builder()
            .ingredientType(SAUCE)
            .name("Mustard sauce")
            .description("Spicy mustard sauce")
            .imageUrl(SAUCE_IMAGES_PATH + "mustardSauce.jpg")
            .grammage(10)
            .calories(BigDecimal.valueOf(15.23))
            .sellingPrice(BigDecimal.valueOf(1.00))
            .purchasePrice(BigDecimal.valueOf(0.95))
            .allergens(new HashSet<>())
            .burgers(new HashSet<>())
            .build();

    final Ingredient savedMustardSauce =
        ingredientService.save(
            enrichIngredientWithRandomAllergens(
                mustardSauce, getRandomObjects(allergens), allergenService));

    final List<Ingredient> ingredients =
        List.of(
            savedChickenMeat,
            savedPorkMeat,
            savedFishMeat,
            savedWhiteBread,
            savedBrownBread,
            savedSesameBread,
            savedGreenSalad,
            savedOnionSalad,
            savedRussianSalad,
            savedBarbequeSauce,
            savedKetchupSauce,
            savedMustardSauce);

    IngredientsLookupTable.initializeWith(ingredients);

    return ingredients;
  }

  private static Ingredient enrichIngredientWithRandomAllergens(
      final Ingredient ingredient,
      final Set<Allergen> allergens,
      final AllergenService allergenService) {

    log.info(
        String.format(
            ">>>>>>>>>> adding %d allergens to %s <<<<<<<<<<",
            allergens.size(), ingredient.getName()));

    allergens.stream()
        .map(a -> allergenService.getById(a.getId()))
        .forEach(
            a -> {
              ingredient.addAllergen(a);
              log.info(
                  String.format("Added %s to %s", a.getAllergenType().type, ingredient.getName()));
            });

    return ingredient;
  }
}
