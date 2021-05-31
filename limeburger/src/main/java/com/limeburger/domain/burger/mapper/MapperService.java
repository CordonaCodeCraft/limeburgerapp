package com.limeburger.domain.burger.mapper;

import com.limeburger.domain.burger.model.admin.BurgerAdminDto;
import com.limeburger.domain.burger.model.customer.BurgerCustomerDto;
import com.limeburger.domain.burger.model.customer.ComposeBurgerCustomerRequest;
import com.limeburger.domain.burger.model.customer.BurgerComposedDto;
import com.limeburger.domain.burger.entity.Burger;
import com.limeburger.domain.ingredient.model.IngredientAdminDto;
import com.limeburger.domain.ingredient.entity.Ingredient;
import com.limeburger.domain.ingredient.repository.IngredientsLookupTable;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;

public interface MapperService {

  @Named("decimalToString")
  static String getFormattedDecimalString(final BigDecimal target) {
    return String.valueOf(target.doubleValue());
  }

  @AfterMapping
  default void setBurgerCustomerDtoPrice(
      final Burger source, @MappingTarget final BurgerCustomerDto target) {
    target.setPrice(getFormattedDecimalString(getCustomerPrice(source)));
  }

  @AfterMapping
  default void setBurgerCustomerDtoGrammage(
      final Burger source, @MappingTarget final BurgerCustomerDto target) {

    final Integer grammageTotal =
        source.getIngredients().stream().map(Ingredient::getGrammage).reduce(0, Integer::sum);

    target.setGrammage(String.valueOf(grammageTotal));
  }

  @AfterMapping
  default void setBurgerComposedDtoPrice(
          final ComposeBurgerCustomerRequest source, @MappingTarget final BurgerComposedDto target) {

    final BigDecimal priceTotal =
        source.getIngredients().stream()
            .map(ingredient -> IngredientsLookupTable.getTable().get(ingredient))
            .map(Ingredient::getSellingPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    target.setPrice(String.valueOf(priceTotal));
  }

  @AfterMapping
  default void setBurgerComposedDtoGrammage(
          final ComposeBurgerCustomerRequest source, @MappingTarget final BurgerComposedDto target) {

    final Integer grammageTotal =
        source.getIngredients().stream()
            .map(ingredient -> IngredientsLookupTable.getTable().get(ingredient))
            .map(Ingredient::getGrammage)
            .reduce(0, Integer::sum);

    target.setGrammage(String.valueOf(grammageTotal));
  }

  @AfterMapping
  default void setBurgerAdminDtoIngredientsCostTotal(
      final Burger source, @MappingTarget final BurgerAdminDto target) {
    target.setIngredientsCostTotal(getFormattedDecimalString(getIngredientsCostTotal(source)));
  }

  @AfterMapping
  default void setBurgerAdminDtoProfitExpected(
      final Burger source, @MappingTarget final BurgerAdminDto target) {

    final BigDecimal ingredientsCostTotal = getIngredientsCostTotal(source);
    final BigDecimal burgerCostTotal = ingredientsCostTotal.add(source.getProductionCost());
    final BigDecimal customerPrice = getCustomerPrice(source);

    target.setProfitExpected(getFormattedDecimalString(customerPrice.subtract(burgerCostTotal)));
  }

  private BigDecimal getIngredientsCostTotal(final Burger source) {
    return source.getIngredients().stream()
        .map(Ingredient::getPurchasePrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getCustomerPrice(final Burger source) {

    final BigDecimal ingredientsSellingPriceTotal =
        source.getIngredients().stream()
            .map(Ingredient::getSellingPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    final BigDecimal profit = ingredientsSellingPriceTotal.multiply(source.getProfitCoefficient());

    BigDecimal customerPrice = ingredientsSellingPriceTotal.add(profit);

    customerPrice =
        source.getIsInPromotion()
            ? customerPrice.subtract(customerPrice.multiply(source.getDiscountCoefficient()))
            : customerPrice;

    return customerPrice;
  }
}
