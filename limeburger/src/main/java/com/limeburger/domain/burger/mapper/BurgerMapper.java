package com.limeburger.domain.burger.mapper;

import com.limeburger.domain.allergen.model.AllergenCustomerDto;
import com.limeburger.domain.allergen.mapper.AllergenMapper;
import com.limeburger.domain.burger.model.admin.BurgerAdminDto;
import com.limeburger.domain.burger.model.customer.BurgerComposedDto;
import com.limeburger.domain.burger.model.customer.BurgerCustomerDto;
import com.limeburger.domain.burger.model.customer.ComposeBurgerCustomerRequest;
import com.limeburger.domain.burger.entity.Burger;
import com.limeburger.domain.ingredient.model.IngredientAdminDto;
import com.limeburger.domain.ingredient.model.IngredientCustomerDto;
import com.limeburger.domain.ingredient.mapper.IngredientMapper;
import com.limeburger.domain.ingredient.entity.Ingredient;
import com.limeburger.domain.ingredient.repository.IngredientsLookupTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.limeburger.domain.burger.entity.Burger.BurgerType;

@Mapper
public interface BurgerMapper extends MapperService {

  BurgerMapper INSTANCE = Mappers.getMapper(BurgerMapper.class);

  @Mapping(source = "burgerType", target = "type", qualifiedByName = "BurgerEnumToBurgerStringValue")
  @Mapping(
      source = "ingredients",
      target = "ingredients",
      qualifiedByName = "ingredientsToCustomerDto")
  @Mapping(
      source = "ingredients",
      target = "allergens",
      qualifiedByName = "ingredientsToAllergenSet")
  @Mapping(
      source = "isInPromotion",
      target = "isInPromotion",
      qualifiedByName = "promotionBooleanToPromotionString")
  BurgerCustomerDto toBurgerCustomerDto(final Burger source);

  @Mapping(source = "burgerType", target = "type", qualifiedByName = "BurgerEnumToBurgerStringValue")
  @Mapping(
      source = "ingredients",
      target = "ingredients",
      qualifiedByName = "ingredientsToAdminDto")
  @Mapping(
      source = "discountCoefficient",
      target = "discountCoefficient",
      qualifiedByName = "decimalToString")
  @Mapping(
      source = "productionCost",
      target = "productionCost",
      qualifiedByName = "decimalToString")
  BurgerAdminDto toBurgerAdminDto(final Burger source);

  @Mapping(
      source = "ingredients",
      target = "ingredients",
      qualifiedByName = "ingredientsListToIngredientSet")
  BurgerComposedDto toBurgerComposedDto(final ComposeBurgerCustomerRequest source);

  @Named("BurgerEnumToBurgerStringValue")
  static String convertBurgerEnumToBurgerStringValue(final BurgerType source) {
    return source.type;
  }

  @Named("promotionBooleanToPromotionString")
  static String convertPromotionBooleanToPromotionStringValue(final Boolean source) {
    return source ? "Burger is in promotion" : "Burger is not in promotion";
  }

  @Named("ingredientsToAllergenSet")
  static Set<AllergenCustomerDto> convertIngredientsToAllergenSet(final Set<Ingredient> source) {
    return source.stream()
        .map(Ingredient::getAllergens)
        .flatMap(set -> set.stream().map(AllergenMapper.INSTANCE::toAllergenCustomerDto))
        .collect(Collectors.toSet());
  }

  @Named("ingredientsToCustomerDto")
  static Set<IngredientCustomerDto> convertIngredientsToCustomerDto(
      final Set<Ingredient> source) {
    return source.stream()
        .map(IngredientMapper.INSTANCE::toIngredientCustomerDto)
        .collect(Collectors.toSet());
  }

  @Named("ingredientsToAdminDto")
  static Set<IngredientAdminDto> convertIngredientsToAdminDto(final Set<Ingredient> source) {
    return source.stream()
        .map(IngredientMapper.INSTANCE::toIngredientAdminDto)
        .collect(Collectors.toSet());
  }

  @Named("ingredientsListToIngredientSet")
  static Set<IngredientCustomerDto> convertIngredientsListToIngredientSet(
      final List<String> source) {
    return source.stream()
        .map(ingredient -> IngredientsLookupTable.getTable().get(ingredient))
        .map(IngredientMapper.INSTANCE::toIngredientCustomerDto)
        .collect(Collectors.toSet());
  }
}
