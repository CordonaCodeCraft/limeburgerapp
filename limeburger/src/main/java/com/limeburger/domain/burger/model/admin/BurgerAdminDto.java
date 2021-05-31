package com.limeburger.domain.burger.model.admin;

import com.limeburger.domain.ingredient.model.IngredientAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@ApiModel(value = "DTO for displaying a burger with attributes, tailored for an admin user")
@Data
public class BurgerAdminDto {

  @ApiModelProperty(value = "String representation of the id", required = true)
  private String id;

  @ApiModelProperty(
      value = "String representation of the burger type (Meat, Vegetarian, Vegan, etc)",
      required = true)
  private String type;

  @ApiModelProperty(value = "Burger's name", required = true)
  private String name;

  @ApiModelProperty(
      value = "Collection of ingredients with attributes, tailored for an admin user",
      required = true)
  private Set<IngredientAdminDto> ingredients;

  @ApiModelProperty(value = "Informs about the promotion status of the burger", required = true)
  private Boolean isInPromotion;

  @ApiModelProperty(value = "Informs about the discount coefficient of the burger", required = true)
  private String discountCoefficient;

  @ApiModelProperty(
      value = "Visualizes the calculation of the total cost of the ingredients of the burger",
      required = true)
  private String ingredientsCostTotal;

  @ApiModelProperty(value = "Informs about the promotion status of the burger", required = true)
  private String productionCost;

  @ApiModelProperty(
      value = "Visualizes the calculation of the expected profit of selling the burger",
      required = true)
  private String profitExpected;
}
