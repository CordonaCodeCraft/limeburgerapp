package com.limeburger.bootstrap.initializers;

import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.repository.service.BurgerService;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.ingredient.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.limeburger.domain.burger.model.Burger.BurgerType.*;

@Component
@Slf4j
public class BurgerInitializer extends Initializer {

  private static final String BURGER_IMAGES_PATH = IMAGES_PATH + "burgers/";

  public static List<Burger> initializeBurgers(
      BurgerService burgerService,
      IngredientService ingredientService,
      List<Ingredient> ingredients) {

    log.info(">>>>>>>>>> INITIALIZING BURGERS... <<<<<<<<< ");

    Burger limeBurger =
        Burger.builder()
            .burgerType(MEAT)
            .name("Lime burger")
            .description("Great burger with a lot of lime and chains")
            .imageUrl(BURGER_IMAGES_PATH + "chickenBurger.jpg")
            .ingredients(new HashSet<>())
            .profitCoefficient(BigDecimal.valueOf(0.10))
            .isInPromotion(true)
            .discountCoefficient(BigDecimal.valueOf(0.5))
            .productionCost(BigDecimal.valueOf(2.00))
            .build();

    Burger savedLimeBurger =
        burgerService.save(
            enrichBurgerWithRandomIngredients(
                limeBurger, getRandomObjects(ingredients), ingredientService));

    Burger hederaBurger =
        Burger.builder()
            .burgerType(MEAT)
            .name("Hedera burger")
            .description("Great burger with a lot of great code")
            .imageUrl(BURGER_IMAGES_PATH + "fishBurger.jpg")
            .ingredients(new HashSet<>())
            .profitCoefficient(BigDecimal.valueOf(0.10))
            .isInPromotion(false)
            .discountCoefficient(BigDecimal.ZERO)
            .productionCost(BigDecimal.valueOf(2.50))
            .build();

    Burger savedHederaBurger =
        burgerService.save(
            enrichBurgerWithRandomIngredients(
                hederaBurger, getRandomObjects(ingredients), ingredientService));

    Burger hyperLedgerBurger =
        Burger.builder()
            .burgerType(MEAT)
            .name("Hyper Ledger burger")
            .description("Great burger with a lot of potential")
            .imageUrl(BURGER_IMAGES_PATH + "porkBurger.jpg")
            .ingredients(new HashSet<>())
            .profitCoefficient(BigDecimal.valueOf(1.00))
            .isInPromotion(false)
            .discountCoefficient(BigDecimal.ZERO)
            .productionCost(BigDecimal.valueOf(3.50))
            .build();

    Burger savedHyperLedgerBurger =
        burgerService.save(
            enrichBurgerWithRandomIngredients(
                hyperLedgerBurger, getRandomObjects(ingredients), ingredientService));

    Burger idoneusBurger =
        Burger.builder()
            .burgerType(VEGAN)
            .name("Idoneus burger")
            .description("Master burger for masters")
            .imageUrl(BURGER_IMAGES_PATH + "veganBurger.jpg")
            .ingredients(new HashSet<>())
            .profitCoefficient(BigDecimal.valueOf(1.00))
            .isInPromotion(true)
            .discountCoefficient(BigDecimal.valueOf(1.00))
            .productionCost(BigDecimal.valueOf(5.50))
            .build();

    Burger savedIdoneusBurger =
        burgerService.save(
            enrichBurgerWithRandomIngredients(
                idoneusBurger, getRandomObjects(ingredients), ingredientService));

    Burger raiffeisenBurger =
        Burger.builder()
            .burgerType(VEGETARIAN)
            .name("Raiffeisen burger")
            .description("Expensive burger for devs with deep pockets")
            .imageUrl(BURGER_IMAGES_PATH + "veggieBurger.jpg")
            .ingredients(new HashSet<>())
            .profitCoefficient(BigDecimal.valueOf(1.00))
            .isInPromotion(false)
            .discountCoefficient(BigDecimal.ZERO)
            .productionCost(BigDecimal.valueOf(10.00))
            .build();

    Burger savedRaiffeisenBurger =
        burgerService.save(
            enrichBurgerWithRandomIngredients(
                raiffeisenBurger, getRandomObjects(ingredients), ingredientService));

    return List.of(
        savedLimeBurger,
        savedHederaBurger,
        savedHyperLedgerBurger,
        savedIdoneusBurger,
        savedRaiffeisenBurger);
  }

  private static Burger enrichBurgerWithRandomIngredients(
      final Burger burger,
      final Set<Ingredient> ingredients,
      final IngredientService ingredientService) {

    log.info(
        String.format(
            ">>>>>>>>>> adding %d ingredients to %s <<<<<<<<<<",
            ingredients.size(), burger.getName()));

    ingredients.stream()
        .map(i -> ingredientService.getById(i.getId()))
        .forEach(
            i -> {
              burger.addIngredient(i);
              log.info(String.format("Added %s to %s", i.getName(), burger.getName()));
            });
    return burger;
  }
}
