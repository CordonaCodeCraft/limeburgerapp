package com.limeburger.domain.burger.model;

import com.limeburger.domain.BaseEntity;
import com.limeburger.domain.ingredient.model.Ingredient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "Burger")
@Table(name = "burgers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Burger extends BaseEntity {

  @NotNull(message = "Burger type can not be empty")
  @Enumerated(value = EnumType.STRING)
  private BurgerType burgerType;

  @Column(unique = true)
  @NotBlank(message = "Burger name can not be empty")
  @Length(
      min = 5,
      max = 20,
      message = "Burger name must be between 5 (inclusive) and 20 (inclusive) characters")
  private String name;

  @Column(unique = true)
  @NotBlank(message = "Burger description can not be empty")
  @Lob
  private String description;

  @Column(unique = true)
  @NotBlank(message = "Burger image URL can not be empty")
  private String imageUrl;

  @ManyToMany(cascade = {PERSIST, MERGE})
  @JoinTable(
      name = "burgers_ingredients",
      joinColumns = @JoinColumn(name = "burger_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private Set<Ingredient> ingredients;

  @NotNull(message = "Profit coefficient can not be null")
  @DecimalMin(value = "0.10", message = "Profit coefficient can not be lesser than 0.10")
  @DecimalMax(value = "1.00", message = "Profit coefficient can not be bigger than 1.00")
  private BigDecimal profitCoefficient;

  @NotNull(message = "Promotion value can not be empty")
  private Boolean isInPromotion;

  @NotNull(message = "Discount coefficient can not be null")
  @DecimalMin(value = "0.10", message = "Discount coefficient can not be lesser than 0.10")
  @DecimalMax(value = "1.50", message = "Discount coefficient can not be bigger than 1.000.50")
  private BigDecimal discountCoefficient;

  @NotNull(message = "Production cost can not be null")
  @DecimalMin(value = "2.00", message = "Profit coefficient can not be lesser than 0.10")
  @DecimalMax(value = "10.00", message = "Profit coefficient can not be bigger than 1.00")
  private BigDecimal productionCost;

  enum BurgerType {
    MEAT("Meat burger"),
    VEGETARIAN("Vegetarian burger"),
    VEGAN("Vegan burger"),
    ;

    public final String type;

    BurgerType(String name) {
      this.type = name;
    }
  }
}
