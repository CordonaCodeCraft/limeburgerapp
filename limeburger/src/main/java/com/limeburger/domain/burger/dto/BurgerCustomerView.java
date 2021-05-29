package com.limeburger.domain.burger.dto;

import com.limeburger.domain.allergen.dto.AllergenCustomerView;
import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import lombok.Data;

import java.util.Set;

@Data
public class BurgerCustomerView {

  private String type;
  private String name;
  private String description;
  private String imageUrl;
  private Set<IngredientCustomerView> ingredients;
  private Set<AllergenCustomerView> allergens;
  private String isInPromotion;
  private String grammage;
  private String price;
}
