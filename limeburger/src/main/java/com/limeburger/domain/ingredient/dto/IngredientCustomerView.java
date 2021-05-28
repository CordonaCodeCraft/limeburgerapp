package com.limeburger.domain.ingredient.dto;

import lombok.Data;

@Data
public class IngredientCustomerView {

  private String name;
  private String description;
  private String imageUrl;
  private String cost;
}
