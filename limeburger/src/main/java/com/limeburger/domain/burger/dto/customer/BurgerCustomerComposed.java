package com.limeburger.domain.burger.dto.customer;

import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import lombok.Data;

import java.util.Set;

@Data
public class BurgerCustomerComposed {

  private String name;
  private Set<IngredientCustomerView> ingredients;
  private String grammage;
  private String price;
}
