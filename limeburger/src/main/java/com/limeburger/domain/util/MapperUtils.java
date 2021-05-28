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
      Burger burger, @MappingTarget BurgerCustomerView burgerCustomerView) {
    burgerCustomerView.setPrice(getFormattedDecimalString(getCustomerPrice(burger)));
  }

  @AfterMapping
  default void setIngredientsCostTotal(
      Burger burger, @MappingTarget BurgerAdminView burgerAdminView) {
    burgerAdminView.setIngredientsCostTotal(
        getFormattedDecimalString(getIngredientsCostTotal(burger)));
  }

  @AfterMapping
  default void setProfitExpected(Burger burger, @MappingTarget BurgerAdminView burgerAdminView) {

    final BigDecimal ingredientsCostTotal = getIngredientsCostTotal(burger);

    final BigDecimal costTotal = ingredientsCostTotal.add(burger.getProductionCost());

    BigDecimal customerPrice = getCustomerPrice(burger);

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

  @AfterMapping
  default void setBurgersContainingIngredient(
      Ingredient ingredient, @MappingTarget IngredientAdminView ingredientAdminView) {
    List<String> burgerNames =
        ingredient.getBurgers().stream()
            .filter(b -> b.getIngredients().contains(ingredient))
            .map(Burger::getName)
            .collect(Collectors.toList());
    ingredientAdminView.setBurgersContainingIngredient(burgerNames);
  }
}
