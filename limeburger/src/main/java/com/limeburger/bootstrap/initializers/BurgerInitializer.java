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

import static com.limeburger.domain.burger.model.Burger.BurgerType.*;

@Component
@Slf4j
public class BurgerInitializer extends Initializer {

  private static final String BURGER_IMAGES_PATH = IMAGES_PATH + "burgers/";

  public static List<Burger> initializeBurgers(
      BurgerService burgerService,
      IngredientService ingredientService,
      List<Ingredient> ingredients) {

    Burger limeBurger =
        burgerService.save(
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
                .build());

    Ingredient limeFirst = ingredientService.getById(ingredients.get(0).getId());
    Ingredient limeSecond = ingredientService.getById(ingredients.get(1).getId());
    Ingredient limeThird = ingredientService.getById(ingredients.get(2).getId());
    Ingredient limeFourth = ingredientService.getById(ingredients.get(5).getId());
    Ingredient limeFifth = ingredientService.getById(ingredients.get(9).getId());

    limeBurger.addIngredient(limeFirst);
    limeBurger.addIngredient(limeSecond);
    limeBurger.addIngredient(limeThird);
    limeBurger.addIngredient(limeFourth);
    limeBurger.addIngredient(limeFifth);

    Burger savedLimeBurger = burgerService.save(limeBurger);

    Burger hederaBurger =
        burgerService.save(
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
                .build());

    Ingredient hederaFirst = ingredientService.getById(ingredients.get(8).getId());
    Ingredient hederaSecond = ingredientService.getById(ingredients.get(3).getId());
    Ingredient hederaThird = ingredientService.getById(ingredients.get(10).getId());
    Ingredient hederaFourth = ingredientService.getById(ingredients.get(9).getId());
    Ingredient hederaFifth = ingredientService.getById(ingredients.get(1).getId());

    hederaBurger.addIngredient(hederaFirst);
    hederaBurger.addIngredient(hederaSecond);
    hederaBurger.addIngredient(hederaThird);
    hederaBurger.addIngredient(hederaFourth);
    hederaBurger.addIngredient(hederaFifth);

    Burger savedHederaBurger = burgerService.save(hederaBurger);

    Burger hyperLedgerBurger =
        burgerService.save(
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
                .build());

    Ingredient hyperLedgerFirst = ingredientService.getById(ingredients.get(11).getId());
    Ingredient hyperLedgerSecond = ingredientService.getById(ingredients.get(0).getId());
    Ingredient hyperLedgerThird = ingredientService.getById(ingredients.get(5).getId());
    Ingredient hyperLedgerFourth = ingredientService.getById(ingredients.get(2).getId());
    Ingredient hyperLedgerFifth = ingredientService.getById(ingredients.get(4).getId());

    hyperLedgerBurger.addIngredient(hyperLedgerFirst);
    hyperLedgerBurger.addIngredient(hyperLedgerSecond);
    hyperLedgerBurger.addIngredient(hyperLedgerThird);
    hyperLedgerBurger.addIngredient(hyperLedgerFourth);
    hyperLedgerBurger.addIngredient(hyperLedgerFifth);

    Burger savedHyperLedgerBurger = burgerService.save(hyperLedgerBurger);

    Burger idoneusBurger =
        burgerService.save(
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
                .build());

    Ingredient idoneusBurgerFirst = ingredientService.getById(ingredients.get(7).getId());
    Ingredient idoneusBurgerSecond = ingredientService.getById(ingredients.get(8).getId());
    Ingredient idoneusBurgerThird = ingredientService.getById(ingredients.get(1).getId());
    Ingredient idoneusBurgerFourth = ingredientService.getById(ingredients.get(10).getId());
    Ingredient idoneusBurgerFifth = ingredientService.getById(ingredients.get(0).getId());

    idoneusBurger.addIngredient(idoneusBurgerFirst);
    idoneusBurger.addIngredient(idoneusBurgerSecond);
    idoneusBurger.addIngredient(idoneusBurgerThird);
    idoneusBurger.addIngredient(idoneusBurgerFourth);
    idoneusBurger.addIngredient(idoneusBurgerFifth);

    Burger savedIdoneusBurger = burgerService.save(idoneusBurger);

    Burger raiffeisenBurger =
        burgerService.save(
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
                .build());

    Ingredient raiffeisenBurgerFirst = ingredientService.getById(ingredients.get(0).getId());
    Ingredient raiffeisenBurgerSecond = ingredientService.getById(ingredients.get(10).getId());
    Ingredient raiffeisenBurgerThird = ingredientService.getById(ingredients.get(9).getId());
    Ingredient raiffeisenBurgerFourth = ingredientService.getById(ingredients.get(7).getId());
    Ingredient raiffeisenBurgerFifth = ingredientService.getById(ingredients.get(6).getId());

    idoneusBurger.addIngredient(raiffeisenBurgerFirst);
    idoneusBurger.addIngredient(raiffeisenBurgerSecond);
    idoneusBurger.addIngredient(raiffeisenBurgerThird);
    idoneusBurger.addIngredient(raiffeisenBurgerFourth);
    idoneusBurger.addIngredient(raiffeisenBurgerFifth);

    Burger savedRaiffeisenBurger = burgerService.save(raiffeisenBurger);

    return List.of(
        savedLimeBurger,
        savedHederaBurger,
        savedHyperLedgerBurger,
        savedIdoneusBurger,
        savedRaiffeisenBurger);
  }
}
