package com.limeburger.domain.burger.mapper;

import com.limeburger.domain.burger.dto.admin.BurgerAdminView;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerCommand;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerComposed;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerView;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.ingredient.repository.IngredientsLookupTable;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;

public interface MapperService {

  @Named("decimalToString")
  static String getFormattedDecimalString(BigDecimal target) {
    return String.valueOf(target.doubleValue());
  }

  @AfterMapping
  default void setBurgerCustomerViewPrice(
      final Burger source, @MappingTarget final BurgerCustomerView target) {
    target.setPrice(getFormattedDecimalString(getCustomerPrice(source)));
  }

  @AfterMapping
  default void setBurgerCustomerViewGrammage(
      final Burger source, @MappingTarget final BurgerCustomerView target) {

    Integer grammageTotal =
        source.getIngredients().stream().map(Ingredient::getGrammage).reduce(0, Integer::sum);

    target.setGrammage(String.valueOf(grammageTotal));
  }

  @AfterMapping
  default void setBurgerCustomerComposedPrice(
      final BurgerCustomerCommand source, @MappingTarget final BurgerCustomerComposed target) {

    BigDecimal price =
        source.getIngredients().stream()
            .map(i -> IngredientsLookupTable.getTable().get(i))
            .map(Ingredient::getSellingPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    target.setPrice(String.valueOf(price));
  }

  @AfterMapping
  default void setBurgerCustomerComposedGrammage(
      final BurgerCustomerCommand source, @MappingTarget final BurgerCustomerComposed target) {

    Integer grammageTotal =
        source.getIngredients().stream()
            .map(i -> IngredientsLookupTable.getTable().get(i))
            .map(Ingredient::getGrammage)
            .reduce(0, Integer::sum);

    target.setGrammage(String.valueOf(grammageTotal));
  }

  @AfterMapping
  default void setBurgerAdminViewIngredientsCostTotal(
      final Burger source, @MappingTarget final BurgerAdminView target) {
    target.setIngredientsCostTotal(getFormattedDecimalString(getIngredientsCostTotal(source)));
  }

  // todo: investigate why List size is zero
  @AfterMapping
  default void setIngredientAdminViewContainingIngredient(
      final Ingredient source, @MappingTarget final IngredientAdminView target) {

    //    final List<String> burgerNames =
    //        ingredient.getBurgers().stream()
    //            .filter(b -> b.getIngredients().contains(ingredient))
    //            .map(Burger::getName)
    //            .collect(Collectors.toList());

    target.setBurgersContainingIngredient(null);
  }

  @AfterMapping
  default void setBurgerAdminViewProfitExpected(
      final Burger source, @MappingTarget final BurgerAdminView target) {

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
