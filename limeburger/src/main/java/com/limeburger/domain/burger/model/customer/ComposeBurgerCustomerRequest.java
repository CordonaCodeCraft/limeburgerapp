package com.limeburger.domain.burger.model.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel(
    value = "Command object for composing new burgers by customer request",
    description =
        "This object is being passed to the Customer API and is being consumed by the \"/api/v1/customer/burgers/compose\" endpoint.\n"
            + "The object contains attributes, required for composing a burger, validated with constraints\n")
@Data
@Builder
public class ComposeBurgerCustomerRequest {

  @ApiModelProperty(
      value =
          "Name of the composed burger, defined by the customer.\n"
              + "Not mandatory.\n"
              + "In the next version burgers, composed without name, will be provided with name, "
              + "following this pattern: \"CustomerName + BurgerType + creation date\"")
  private String name;

  @ApiModelProperty(
      value =
          "A collection of the names of the ingredients in the composed burger (min 3 / max 10)",
      required = true)
  @NotNull
  @Size(min = 3, max = 10, message = "Ingredients count can not be less than 3 and more than 10")
  private List<String> ingredients;
}
