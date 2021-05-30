package com.limeburger.domain.burger.model.customer;

import com.limeburger.domain.ingredient.model.IngredientCustomerDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@ApiModel(value = "DTO for displaying burger, composed by a customer")
@Data
public class BurgerComposedDto {

  @ApiModelProperty(value = "Name of the composed burger", required = true)
  private String name;

  @ApiModelProperty(
      value = "Collection of ingredients with attributes, tailored for a customer user",
      required = true)
  private Set<IngredientCustomerDto> ingredients;

  @ApiModelProperty(
      value =
          "Visualizes the calculation of the total grammage of the composed burger by summing the grammage of the ingredients",
      required = true)
  private String grammage;

  @ApiModelProperty(
      value =
          "Visualizes the calculation of the total price of the composed burger by summing the purchase prise of the ingredients",
      required = true)
  private String price;
}
