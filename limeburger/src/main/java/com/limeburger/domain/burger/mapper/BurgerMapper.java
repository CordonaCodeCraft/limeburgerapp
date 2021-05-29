package com.limeburger.domain.burger.mapper;

import com.limeburger.domain.allergen.dto.AllergenCustomerView;
import com.limeburger.domain.allergen.mapper.AllergenMapper;
import com.limeburger.domain.burger.dto.BurgerAdminView;
import com.limeburger.domain.burger.dto.BurgerCustomerView;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import com.limeburger.domain.ingredient.mapper.IngredientMapper;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.util.MapperService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

import static com.limeburger.domain.burger.model.Burger.BurgerType;

@Mapper
public interface BurgerMapper extends MapperService {

  BurgerMapper INSTANCE = Mappers.getMapper(BurgerMapper.class);

  @Mapping(source = "burgerType", target = "type", qualifiedByName = "BurgerEnumToBurgerEnumValue")
  @Mapping(
      source = "ingredients",
      target = "ingredients",
      qualifiedByName = "ingredientsToCustomerView")
  @Mapping(
      source = "ingredients",
      target = "allergens",
      qualifiedByName = "ingredientsToAllergenSet")
  @Mapping(
      source = "isInPromotion",
      target = "isInPromotion",
      qualifiedByName = "promotionBooleanToPromotionString")
  BurgerCustomerView toBurgerCustomerView(Burger burger);

  @Mapping(source = "burgerType", target = "type", qualifiedByName = "BurgerEnumToBurgerEnumValue")
  @Mapping(
      source = "ingredients",
      target = "ingredients",
      qualifiedByName = "ingredientsToAdminView")
  @Mapping(
      source = "discountCoefficient",
      target = "discountCoefficient",
      qualifiedByName = "decimalToString")
  @Mapping(
      source = "productionCost",
      target = "productionCost",
      qualifiedByName = "decimalToString")
  BurgerAdminView toBurgerAdminView(final Burger burger);

  @Named("BurgerEnumToBurgerEnumValue")
  static String convertBurgerEnumToBurgerStringValue(final BurgerType target) {
    return target.type;
  }

  @Named("promotionBooleanToPromotionString")
  static String convertPromotionBooleanToPromotionStringValue(final Boolean target) {
    return target ? "Burger is in promotion" : "Burger is not in promotion";
  }

  @Named("ingredientsToAllergenSet")
  static Set<AllergenCustomerView> convertIngredientsToAllergenSet(final Set<Ingredient> target) {
    return target.stream()
        .map(Ingredient::getAllergens)
        .flatMap(s -> s.stream().map(AllergenMapper.INSTANCE::toAllergenCustomerView))
        .collect(Collectors.toSet());
  }

  @Named("ingredientsToCustomerView")
  static Set<IngredientCustomerView> convertIngredientsToCustomerView(
      final Set<Ingredient> target) {
    return target.stream()
        .map(IngredientMapper.INSTANCE::toIngredientCustomerView)
        .collect(Collectors.toSet());
  }

  @Named("ingredientsToAdminView")
  static Set<IngredientAdminView> convertIngredientsToAdminView(final Set<Ingredient> target) {
    return target.stream()
        .map(IngredientMapper.INSTANCE::toIngredientAdminView)
        .collect(Collectors.toSet());
  }
}
