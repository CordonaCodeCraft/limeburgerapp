package com.limeburger.domain.burger.dto.customer;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class BurgerCustomerCommand {

  private String name;

  @NotNull
  @Size(min = 3, max = 10, message = "Ingredients count can not be less than 3 and more than 10")
  private List<String> ingredients;
}
