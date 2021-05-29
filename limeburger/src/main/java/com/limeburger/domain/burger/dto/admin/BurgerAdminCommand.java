package com.limeburger.domain.burger.dto.admin;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

import static com.limeburger.domain.burger.model.Burger.BurgerType;

@Data
@Builder
public class BurgerAdminCommand {

  @NotNull(message = "Burger type can not be empty")
  private BurgerType burgerType;

  @NotBlank(message = "Burger name can not be empty")
  @Length(
      min = 5,
      max = 20,
      message = "Burger name must be between 5 (inclusive) and 20 (inclusive) characters")
  private String name;

  @NotBlank(message = "Burger description can not be empty")
  @Length(
      min = 20,
      max = 255,
      message = "Burger description must be between 20 (inclusive) and 255 (inclusive) characters")
  private String description;

  @URL
  @NotBlank(message = "Burger image URL can not be empty")
  private String imageUrl;

  @NotNull
  @Size(min = 3, max = 10, message = "Ingredients count can not be less than 3 and more than 10")
  private List<String> ingredients;

  @NotNull(message = "Profit coefficient can not be null")
  @DecimalMin(value = "0.10", message = "Profit coefficient can not be lesser than 0.10")
  @DecimalMax(value = "3.00", message = "Profit coefficient can not be bigger than 3.00")
  private BigDecimal profitCoefficient;

  @NotNull(message = "Promotion value can not be empty")
  private Boolean isInPromotion;

  @NotNull(message = "Discount coefficient can not be null")
  @PositiveOrZero
  @DecimalMax(value = "0.5", message = "Discount coefficient can not be bigger than 0.5")
  private BigDecimal discountCoefficient;

  @NotNull(message = "Production cost can not be null")
  @DecimalMin(value = "1.00", message = "Product cost can not be lesser than 1.00")
  @DecimalMax(value = "10.00", message = "Product cost can not be bigger than 10.00")
  private BigDecimal productionCost;
}
