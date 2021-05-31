package com.limeburger.domain.allergen.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "DTO for displaying an allergen with attributes, tailored for a customer user")
@Data
public class AllergenCustomerDto {

  @ApiModelProperty(value = "Allergen's name", required = true)
  private String name;

  @ApiModelProperty(value = "Allergen's name", required = true)
  private String description;

  @ApiModelProperty(value = "Allergen's danger index", required = true)
  private String dangerIndex;

  @ApiModelProperty(value = "URL string pointing at image of the allergen", required = true)
  private String imageUrl;
}
