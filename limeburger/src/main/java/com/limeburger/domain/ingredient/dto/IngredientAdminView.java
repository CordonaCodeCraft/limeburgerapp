package com.limeburger.domain.ingredient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "DTO for displaying an ingredient with attributes, tailored for a admin user")
@Data
public class IngredientAdminView {

  @ApiModelProperty(value = "String representation of the id", required = true)
  private String id;

  @ApiModelProperty(value = "String representation of the ingredient type (Milk, Gluten, Fish, etc)", required = true)
  private String type;

  @ApiModelProperty(value = "Ingredient's name", required = true)
  private String name;

  @ApiModelProperty(value = "Ingredient's description", required = true)
  private String description;

  @ApiModelProperty(value = "Ingredient's grammage", required = true)
  private String grammage;

  @ApiModelProperty(value = "Ingredient's selling price", required = true)
  private String sellingPrice;

  @ApiModelProperty(value = "Ingredient's purchase price", required = true)
  private String purchasePrice;

  @ApiModelProperty(value = "A collection of burgers names, containing this ingredient", required = true)
  private List<String> burgersContainingIngredient;
}
