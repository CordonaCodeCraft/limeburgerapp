package com.limeburger.domain.util;

import com.limeburger.domain.burger.dto.BurgerAdminView;
import com.limeburger.domain.burger.dto.BurgerCustomerView;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.model.Ingredient;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public interface MapperUtils {

  @Named("decimalToString")
  static String getFormattedDecimalString(BigDecimal target) {
    return String.valueOf(target.doubleValue());
  }

  @AfterMapping
  default void setCustomerPrice(
      final Burger burger, @MappingTarget final BurgerCustomerView burgerCustomerView) {
    burgerCustomerView.setPrice(getFormattedDecimalString(getCustomerPrice(burger)));
  }

  @AfterMapping
  default void setIngredientsCostTotal(
      final Burger burger, @MappingTarget final BurgerAdminView burgerAdminView) {
    burgerAdminView.setIngredientsCostTotal(
        getFormattedDecimalString(getIngredientsCostTotal(burger)));
  }

  @AfterMapping
  default void setBurgersContainingIngredient(
      final Ingredient ingredient, @MappingTarget final IngredientAdminView ingredientAdminView) {

    final List<String> burgerNames =
        ingredient.getBurgers().stream()
            .filter(b -> b.getIngredients().contains(ingredient))
            .map(Burger::getName)
            .collect(Collectors.toList());

    ingredientAdminView.setBurgersContainingIngredient(burgerNames);
  }

  @AfterMapping
  default void setProfitExpected(
      final Burger burger, @MappingTarget final BurgerAdminView burgerAdminView) {

    final BigDecimal ingredientsCostTotal = getIngredientsCostTotal(burger);

    final BigDecimal costTotal = ingredientsCostTotal.add(burger.getProductionCost());

    final BigDecimal customerPrice = getCustomerPrice(burger);

    burgerAdminView.setProfitExpected(getFormattedDecimalString(customerPrice.subtract(costTotal)));
  }

  private BigDecimal getIngredientsCostTotal(Burger burger) {
    return burger.getIngredients().stream()
        .map(Ingredient::getPurchasePrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getCustomerPrice(final Burger burger) {

    final BigDecimal ingredientsSellingPriceTotal =
        burger.getIngredients().stream()
            .map(Ingredient::getSellingPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    final BigDecimal profit = ingredientsSellingPriceTotal.multiply(burger.getProfitCoefficient());

    BigDecimal customerPrice = ingredientsSellingPriceTotal.add(profit);

    customerPrice =
        burger.getIsInPromotion()
            ? customerPrice.subtract(customerPrice.multiply(burger.getDiscountCoefficient()))
            : customerPrice;

    return customerPrice;
  }
}
