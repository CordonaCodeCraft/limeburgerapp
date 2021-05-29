package com.limeburger.domain.burger.dto.admin;

import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import lombok.Data;

import java.util.Set;

@Data
public class BurgerAdminView {

  private String id;
  private String type;
  private String name;
  private Set<IngredientAdminView> ingredients;
  private Boolean isInPromotion;
  private String discountCoefficient;
  private String ingredientsCostTotal;
  private String productionCost;
  private String profitExpected;
}
