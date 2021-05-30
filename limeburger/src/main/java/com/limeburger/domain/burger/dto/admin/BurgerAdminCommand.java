package com.limeburger.domain.burger.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

import static com.limeburger.domain.burger.model.Burger.BurgerType;

@ApiModel(
    value = "Command object for creating new burgers by an admin user",
    description =
        "This object is being passed to the Admin API and is being consumed by the \"/api/v1/admin/burgers/create\" endpoint.\n"
            + "The object contains attributes, required for creating a new burger, validated with constraints\n")
@Data
@Builder
public class BurgerAdminCommand {

  @ApiModelProperty(value = "Enum for burger types", required = true)
  @NotNull(message = "Burger type can not be empty")
  private BurgerType burgerType;

  @ApiModelProperty(value = "Burger's name (min 5 / max 20 characters", required = true)
  @NotBlank(message = "Burger name can not be empty")
  @Length(
      min = 5,
      max = 20,
      message = "Burger name must be between 5 (inclusive) and 20 (inclusive) characters")
  private String name;

  @ApiModelProperty(value = "Burger's description (min 20 / max 255 characters)", required = true)
  @NotBlank(message = "Burger description can not be empty")
  @Length(
      min = 20,
      max = 255,
      message = "Burger description must be between 20 (inclusive) and 255 (inclusive) characters")
  private String description;

  @ApiModelProperty(value = "URL string pointing at image of the burger", required = true)
  @URL
  @NotBlank(message = "Burger image URL can not be empty")
  private String imageUrl;

  @ApiModelProperty(
      value =
          "List of the names of the ingredients, which will be resolved by the backend to ingredient objects (min 3 / max 10)",
      required = true)
  @NotNull
  @Size(min = 3, max = 10, message = "Ingredients count can not be less than 3 and more than 10")
  private List<String> ingredients;

  @ApiModelProperty(
      value = "Profit coefficient, used for the calculation of the expected profit (min 0.10 / max 3.00)",
      required = true)
  @NotNull(message = "Profit coefficient can not be null")
  @DecimalMin(value = "0.10", message = "Profit coefficient can not be lesser than 0.10")
  @DecimalMax(value = "3.00", message = "Profit coefficient can not be bigger than 3.00")
  private BigDecimal profitCoefficient;

  @ApiModelProperty(
      value = "Attribute, required for the proper calculation of the expected profit",
      required = true)
  @NotNull(message = "Promotion value can not be empty")
  private Boolean isInPromotion;

  @ApiModelProperty(
      value =
          "The discount coefficient, used for the calculation of the price of the burger if in promotion (min 0 / max 0.5)",
      required = true)
  @NotNull(message = "Discount coefficient can not be null")
  @PositiveOrZero
  @DecimalMax(value = "0.5", message = "Discount coefficient can not be bigger than 0.5")
  private BigDecimal discountCoefficient;

  @ApiModelProperty(
      value =
          "The production cost of the burger, used for the calculation of the price and the profit of the burger (min 1.00, max 10.00).\n"
              + "In v1.0 is predefined value, representing the sum of costs like salaries, electricity, water, taxes etc",
      required = true)
  @NotNull(message = "Production cost can not be null")
  @DecimalMin(value = "1.00", message = "Product cost can not be lesser than 1.00")
  @DecimalMax(value = "10.00", message = "Product cost can not be bigger than 10.00")
  private BigDecimal productionCost;
}
