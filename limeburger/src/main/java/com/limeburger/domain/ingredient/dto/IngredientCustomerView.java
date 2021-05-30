package com.limeburger.domain.ingredient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "DTO for displaying an ingredient with attributes, tailored for a customer user")
@Data
public class IngredientCustomerView {

  @ApiModelProperty(value = "Ingredient's name", required = true)
  private String name;

  @ApiModelProperty(value = "Ingredient's description", required = true)
  private String description;

  @ApiModelProperty(value = "URL string pointing at image of the ingredient", required = true)
  private String imageUrl;

  @ApiModelProperty(
      value = "The price of the ingredient, used for the calculation of the price of the burger",
      required = true)
  private String cost;
}
