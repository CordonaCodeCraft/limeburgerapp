package com.limeburger.domain.ingredient.dto;

import lombok.Data;

import java.util.List;

@Data
public class IngredientAdminView {

  private String type;
  private String name;
  private String description;
  private String grammage;
  private String sellingPrice;
  private String purchasePrice;
  private List<String> burgersContainingIngredient;
}
