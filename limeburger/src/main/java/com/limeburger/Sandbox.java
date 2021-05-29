package com.limeburger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limeburger.domain.allergen.model.Allergen;
import com.limeburger.domain.burger.dto.admin.BurgerAdminCommand;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerView;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.ingredient.model.Ingredient;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.limeburger.domain.allergen.model.Allergen.AllergenType.*;
import static com.limeburger.domain.burger.model.Burger.BurgerType.MEAT;
import static com.limeburger.domain.ingredient.model.Ingredient.IngredientType;
import static com.limeburger.domain.ingredient.model.Ingredient.IngredientType.*;
import static com.limeburger.domain.ingredient.model.Ingredient.builder;

public class Sandbox {

  public static void main(String[] args) throws JsonProcessingException {

    BurgerAdminCommand burgerAdminCommand =
        BurgerAdminCommand.builder()
            .burgerType(MEAT)
            .name("Uber new burger")
            .description("This is uber new burger for limers")
            .imageUrl(
                "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2019_21/2870431/190524-classic-american-cheeseburger-ew-207p-2870431.jpg")
            .ingredients(List.of("Chicken meat", "White bread", "Onion salad", "Barbeque sauce"))
            .profitCoefficient(BigDecimal.valueOf(2.00))
            .isInPromotion(false)
            .discountCoefficient(BigDecimal.valueOf(0.5))
            .productionCost(BigDecimal.valueOf(1.00))
            .build();

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(burgerAdminCommand);

    System.out.println(jsonString);

    final Allergen crustacean =
        Allergen.builder()
            .allergenType(CRUSTACEAN)
            .description("Dangerous allergen, causing addiction to block chain")
            .imageUrl("src/main/resources/static/images/allergens/corn.svg")
            .dangerIndex(10)
            .ingredients(new HashSet<>())
            .build();

    final Allergen egg =
        Allergen.builder()
            .allergenType(EGG)
            .description("Cute allergen causing addiction to cryptocurrency")
            .imageUrl("src/main/resources/static/images/allergens/corn.svg")
            .dangerIndex(2)
            .ingredients(new HashSet<>())
            .build();

    final Allergen fish =
        Allergen.builder()
            .allergenType(FISH)
            .description("Lethal allergen causing permanent affection to Hedera Hashgraph")
            .imageUrl("src/main/resources/static/images/allergens/corn.svg")
            .dangerIndex(10)
            .ingredients(new HashSet<>())
            .build();

    final Allergen peanut =
        Allergen.builder()
            .allergenType(PEANUT)
            .description("Nasty allergen causing unexpected block chain behaviour")
            .imageUrl("src/main/resources/static/images/allergens/corn.svg")
            .dangerIndex(9)
            .ingredients(new HashSet<>())
            .build();

    final Ingredient ketchupSauce =
        builder()
            .ingredientType(SAUCE)
            .name("Ketchup sauce")
            .description("Bloody ketchup sauce")
            .imageUrl("src/main/resources/static/images/ingredients/breads/brownBread.jpg")
            .grammage(10)
            .calories(BigDecimal.valueOf(10.84))
            .sellingPrice(BigDecimal.valueOf(1.00))
            .purchasePrice(BigDecimal.valueOf(0.60))
            .allergens(Set.of(peanut, fish))
            .burgers(
                Set.of(
                    Burger.builder().name("First").build(),
                    Burger.builder().name("Second").build(),
                    Burger.builder().name("Third").build()))
            .build();

    final Ingredient sesameBread =
        builder()
            .ingredientType(BREAD)
            .name("Sesame bread")
            .description("Exotic sesame bread")
            .imageUrl("src/main/resources/static/images/ingredients/breads/sesameBread.jpg")
            .grammage(80)
            .calories(BigDecimal.valueOf(90.92))
            .sellingPrice(BigDecimal.valueOf(2.50))
            .purchasePrice(BigDecimal.valueOf(0.65))
            .allergens(Set.of(crustacean, egg))
            .burgers(new HashSet<>())
            .build();

    final Ingredient russianSalad =
        builder()
            .ingredientType(SALAD)
            .name("Russian salad")
            .description("Calorie bomb russian salad")
            .imageUrl("src/main/resources/static/images/ingredients/salads/russianSalad.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(249.23))
            .sellingPrice(BigDecimal.valueOf(2.50))
            .purchasePrice(BigDecimal.valueOf(0.85))
            .allergens(Set.of(fish, peanut))
            .burgers(new HashSet<>())
            .build();

    final Ingredient chickenMeat =
        builder()
            .ingredientType(IngredientType.MEAT)
            .name("Chicken meat")
            .description("Tasty chicken meat")
            .imageUrl("src/main/resources/static/images/ingredients/meats/chickenMeat.jpg")
            .grammage(100)
            .calories(BigDecimal.valueOf(100.25))
            .sellingPrice(BigDecimal.valueOf(2.00))
            .purchasePrice(BigDecimal.valueOf(1.00))
            .allergens(Set.of(crustacean, peanut))
            .burgers(new HashSet<>())
            .build();

    Burger limeBurger =
        Burger.builder()
            .burgerType(MEAT)
            .name("Lime burger")
            .description("Great burger with a lot of lime and chains")
            .imageUrl("src/main/resources/static/images/burgers/chickenBurger.jpg")
            .ingredients(Set.of(sesameBread, russianSalad, chickenMeat))
            .profitCoefficient(BigDecimal.valueOf(0.5))
            .isInPromotion(true)
            .discountCoefficient(BigDecimal.valueOf(0.5))
            .productionCost(BigDecimal.valueOf(2.00))
            .build();

    BurgerCustomerView test = BurgerMapper.INSTANCE.toBurgerCustomerView(limeBurger);

    System.out.println();
  }
}
