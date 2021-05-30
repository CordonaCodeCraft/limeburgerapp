package com.limeburger.domain.burger.model.customer;

import com.limeburger.domain.allergen.model.AllergenCustomerDto;
import com.limeburger.domain.ingredient.model.IngredientCustomerDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@ApiModel(value = "DTO for displaying a burger with attributes, tailored for a customer user")
@Data
public class BurgerCustomerDto {

  @ApiModelProperty(
      value = "String representation of the burger type (Meat, Vegetarian, Vegan, etc)",
      required = true)
  private String type;

  @ApiModelProperty(value = "Burger's name", required = true)
  private String name;

  @ApiModelProperty(value = "Burger's description", required = true)
  private String description;

  @ApiModelProperty(value = "URL string pointing at image of the burger", required = true)
  private String imageUrl;

  @ApiModelProperty(
      value = "Collection of ingredients with attributes, tailored for a customer user",
      required = true)
  private Set<IngredientCustomerDto> ingredients;

  @ApiModelProperty(
      value = "Collection of allergens with attributes, tailored for a customer user",
      required = true)
  private Set<AllergenCustomerDto> allergens;

  @ApiModelProperty(value = "Informs about the promotion status of the burger", required = true)
  private String isInPromotion;

  @ApiModelProperty(
      value =
          "Visualizes the calculation of the total grammage of the composed burger by summing the grammage of the ingredients",
      required = true)
  private String grammage;

  @ApiModelProperty(
      value =
          "Visualizes the calculation of the total price of the composed burger by summing the purchase prise of the ingredients.\n"
              + "The formula considers the promotion status of the burger and the profit coefficient",
      required = true)
  private String price;
}
