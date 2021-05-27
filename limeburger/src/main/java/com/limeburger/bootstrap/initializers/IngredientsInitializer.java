package com.limeburger.bootstrap.initializers;

import com.limeburger.domain.allergen.model.Allergen;
import com.limeburger.domain.allergen.service.AllergenService;
import com.limeburger.domain.ingredient.model.Ingredient;
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

    Ingredient chickenMeat =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(MEAT)
                .name("Chicken meat")
                .description("Tasty chicken meat")
                .imageUrl(MEAT_IMAGES_PATH + "chickenMeat.jpg")
                .grammage(100)
                .calories(BigDecimal.valueOf(100.25))
                .price(BigDecimal.valueOf(2.00))
                .cost(BigDecimal.valueOf(1.00))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedChickenMeat =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                chickenMeat, getRandomObjects(allergens), allergenService));

    Ingredient fishMeat =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(MEAT)
                .name("Fish meat")
                .description("Crispy fish meat")
                .imageUrl(MEAT_IMAGES_PATH + "fishMeat.jpg")
                .grammage(100)
                .calories(BigDecimal.valueOf(85.96))
                .price(BigDecimal.valueOf(1.50))
                .cost(BigDecimal.valueOf(0.50))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedFishMeat =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                fishMeat, getRandomObjects(allergens), allergenService));

    Ingredient porkMeat =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(MEAT)
                .name("Pork meat")
                .description("Yummy yummy pork meat")
                .imageUrl(MEAT_IMAGES_PATH + "porkMeat.jpg")
                .grammage(100)
                .calories(BigDecimal.valueOf(120.89))
                .price(BigDecimal.valueOf(2.50))
                .cost(BigDecimal.valueOf(1.50))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedPorkMeat =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                porkMeat, getRandomObjects(allergens), allergenService));

    Ingredient whiteBread =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(BREAD)
                .name("White bread")
                .description("Gentle white bread")
                .imageUrl(BREAD_IMAGES_PATH + "whiteBread.jpg")
                .grammage(80)
                .calories(BigDecimal.valueOf(90.21))
                .price(BigDecimal.valueOf(1.00))
                .cost(BigDecimal.valueOf(0.75))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedWhiteBread =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                whiteBread, getRandomObjects(allergens), allergenService));

    Ingredient brownBread =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(BREAD)
                .name("Brown bread")
                .description("Soft brown bread")
                .imageUrl(BREAD_IMAGES_PATH + "brownBread.jpg")
                .grammage(80)
                .calories(BigDecimal.valueOf(40.22))
                .price(BigDecimal.valueOf(1.50))
                .cost(BigDecimal.valueOf(0.60))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedBrownBread =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                brownBread, getRandomObjects(allergens), allergenService));

    Ingredient sesameBread =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(BREAD)
                .name("Sesame bread")
                .description("Exotic sesame bread")
                .imageUrl(BREAD_IMAGES_PATH + "sesameBread.jpg")
                .grammage(80)
                .calories(BigDecimal.valueOf(90.92))
                .price(BigDecimal.valueOf(2.50))
                .cost(BigDecimal.valueOf(0.65))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedSesameBread =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                sesameBread, getRandomObjects(allergens), allergenService));

    Ingredient greenSalad =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SALAD)
                .name("Green salad")
                .description("Healthy green salad")
                .imageUrl(SALAD_IMAGES_PATH + "greenSalad.jpg")
                .grammage(50)
                .calories(BigDecimal.valueOf(25.25))
                .price(BigDecimal.valueOf(1.50))
                .cost(BigDecimal.valueOf(0.65))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedGreenSalad =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                greenSalad, getRandomObjects(allergens), allergenService));

    Ingredient onionSalad =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SALAD)
                .name("Onion salad")
                .description("Stinky onion salad")
                .imageUrl(SALAD_IMAGES_PATH + "onionSalad.jpg")
                .grammage(50)
                .calories(BigDecimal.valueOf(10.25))
                .price(BigDecimal.valueOf(3.50))
                .cost(BigDecimal.valueOf(0.55))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedOnionSalad =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                onionSalad, getRandomObjects(allergens), allergenService));

    Ingredient russianSalad =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SALAD)
                .name("Russian salad")
                .description("Calorie bomb russian salad")
                .imageUrl(SALAD_IMAGES_PATH + "russianSalad.jpg")
                .grammage(100)
                .calories(BigDecimal.valueOf(249.23))
                .price(BigDecimal.valueOf(2.50))
                .cost(BigDecimal.valueOf(0.85))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedRussianSalad =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                russianSalad, getRandomObjects(allergens), allergenService));

    Ingredient barbequeSauce =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SAUCE)
                .name("Barbeque sauce")
                .description("Tasty barbeque sauce")
                .imageUrl(SAUCE_IMAGES_PATH + "barbequeSauce.jpg")
                .grammage(10)
                .calories(BigDecimal.valueOf(18.24))
                .price(BigDecimal.valueOf(1.00))
                .cost(BigDecimal.valueOf(0.50))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedBarbequeSauce =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                barbequeSauce, getRandomObjects(allergens), allergenService));

    Ingredient ketchupSauce =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SAUCE)
                .name("Ketchup sauce")
                .description("Bloody ketchup sauce")
                .imageUrl(SAUCE_IMAGES_PATH + "ketchupSauce.jpg")
                .grammage(10)
                .calories(BigDecimal.valueOf(10.84))
                .price(BigDecimal.valueOf(1.00))
                .cost(BigDecimal.valueOf(0.60))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedKetchupSauce =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                ketchupSauce, getRandomObjects(allergens), allergenService));

    Ingredient mustardSauce =
        ingredientService.save(
            Ingredient.builder()
                .ingredientType(SAUCE)
                .name("Mustard sauce")
                .description("Spicy mustard sauce")
                .imageUrl(SAUCE_IMAGES_PATH + "mustardSauce.jpg")
                .grammage(10)
                .calories(BigDecimal.valueOf(15.23))
                .price(BigDecimal.valueOf(1.00))
                .cost(BigDecimal.valueOf(0.95))
                .allergens(new HashSet<>())
                .burgers(new HashSet<>())
                .build());

    Ingredient savedMustardSauce =
        ingredientService.save(
            getIngredientWithRandomAllergens(
                mustardSauce, getRandomObjects(allergens), allergenService));

    return List.of(
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
  }

  private static Ingredient getIngredientWithRandomAllergens(
      final Ingredient savedIngredient,
      final Set<Allergen> randomAllergens,
      final AllergenService allergenService) {

    log.info(
        String.format(">>>>>>>>>> adding allergens to %s <<<<<<<<<<", savedIngredient.getName()));

    randomAllergens.stream()
        .map(a -> allergenService.getById(a.getId()))
        .forEach(
            a -> {
              savedIngredient.addAllergen(a);
              log.info(
                  String.format(
                      "Added %s to %s", a.getAllergenType().type, savedIngredient.getName()));
            });

    return savedIngredient;
  }
}
