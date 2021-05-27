package com.limeburger.domain.burger.model;

import com.limeburger.domain.BaseEntity;
import com.limeburger.domain.ingredient.model.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "Burger")
@Table(name = "burgers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
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
  @Length(
      min = 20,
      max = 255,
      message = "Burger description must be between 20 (inclusive) and 255 (inclusive) characters")
  private String description;

  @Column(unique = true)
  @NotBlank(message = "Burger image URL can not be empty")
  private String imageUrl;

  @ManyToMany()
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
  @PositiveOrZero
  @DecimalMax(value = "1.50", message = "Discount coefficient can not be bigger than 1.50")
  private BigDecimal discountCoefficient;
  @NotNull(message = "Production cost can not be null")
  @DecimalMin(value = "2.00", message = "Product cost can not be lesser than 2.00")
  @DecimalMax(value = "10.00", message = "Product cost can not be bigger than 10.00")
  private BigDecimal productionCost;

  public void addIngredient(Ingredient ingredient) {
    this.getIngredients().add(ingredient);
    ingredient.getBurgers().add(this);
  }

  @Override
  public boolean equals(Object other) {

    if (other == null) {
      return false;
    }
    if (other == this) {
      return true;
    }
    if (other.getClass() != getClass()) {
      return false;
    }

    Burger burger = (Burger) other;

    return new EqualsBuilder().append(this.name, burger.name).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.name).toHashCode();
  }

  @Override
  public String toString() {
    return "Burger{"
        + "burgerType="
        + burgerType
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", imageUrl='"
        + imageUrl
        + '\''
        + ", profitCoefficient="
        + profitCoefficient
        + ", isInPromotion="
        + isInPromotion
        + ", discountCoefficient="
        + discountCoefficient
        + ", productionCost="
        + productionCost
        + '}';
  }

  public enum BurgerType {
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
