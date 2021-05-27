package com.limeburger.domain.ingredient.model;

import com.limeburger.domain.BaseEntity;
import com.limeburger.domain.allergen.model.Allergen;
import com.limeburger.domain.burger.model.Burger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Ingredient")
@Table(name = "ingredients")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Ingredient extends BaseEntity {

  @NotNull(message = "Ingredient type can not be empty")
  @Enumerated(value = EnumType.STRING)
  private IngredientType ingredientType;

  @Column(unique = true)
  @NotBlank(message = "Ingredient name can not be empty")
  @Length(
      min = 5,
      max = 15,
      message = "Ingredient name must be between 5 (inclusive) and 15 (inclusive) characters")
  private String name;

  @Column(unique = true)
  @NotBlank(message = "Description can not be empty")
  @Length(
      min = 10,
      max = 255,
      message = "Description must be between 10 (inclusive) and 255 (inclusive) characters")
  private String description;

  @Column(unique = true)
  @NotBlank(message = "Ingredient image URL can not be empty")
  private String imageUrl;

  @NotNull(message = "Ingredient grammage can not be empty")
  @Positive(message = "Grammage must be positive value")
  @Range(
      min = 10,
      max = 100,
      message = "Ingredient grammage must be between 10 (inclusive) and 100 (inclusive) grams")
  private Integer grammage;

  @NotNull(message = "Calories can not be empty")
  @Positive(message = "Calories value must be positive real digit")
  @DecimalMin(value = "1.00", message = "Calories value can not be lesser than 1.00")
  @DecimalMax(value = "250.00", message = "Calories value can not be bigger than 250.00")
  private BigDecimal calories;

  @NotNull(message = "Price can not be empty")
  @Positive(message = "Price value must be positive real digit")
  @DecimalMin(value = "0.50", message = "Price value can not be lesser than 0.50")
  @DecimalMax(value = "5.00", message = "Price value can not be bigger than 5.00")
  private BigDecimal price;

  @NotNull(message = "Cost can not be empty")
  @Positive(message = "Cost value must be positive real digit")
  @DecimalMin(value = "0.50", message = "Cost value can not be lesser than 0.50")
  @DecimalMax(value = "2.00", message = "Cost value can not be bigger than 5.00")
  private BigDecimal cost;

  @ManyToMany(mappedBy = "ingredients")
  private Set<Burger> burgers = new HashSet<>();

  @ManyToMany()
  @JoinTable(
      name = "ingredients_allergens",
      joinColumns = @JoinColumn(name = "ingredient_id"),
      inverseJoinColumns = @JoinColumn(name = "allergen_id"))
  private Set<Allergen> allergens;

  public void addAllergen(Allergen allergen) {
    this.allergens.add(allergen);
    allergen.getIngredients().add(this);
  }

  public void addAllergens(List<Allergen> allergens) {
    allergens.forEach(
        a -> {
          this.allergens.add(a);
          a.getIngredients().add(this);
        });
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

    Ingredient ingredient = (Ingredient) other;

    return new EqualsBuilder().append(this.name, ingredient.name).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.name).toHashCode();
  }

  @Override
  public String toString() {
    return "Ingredient{"
        + "ingredientType="
        + ingredientType
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", imageUrl='"
        + imageUrl
        + '\''
        + ", grammage="
        + grammage
        + ", calories="
        + calories
        + ", price="
        + price
        + ", cost="
        + cost
        + '}';
  }

  public enum IngredientType {
    BREAD("Bread"),
    MEAT("Meat"),
    SALAD("Salad"),
    SAUCE("Sauce"),
    ADD_ON("Add-on"),
    ;

    public final String type;

    IngredientType(String name) {
      this.type = name;
    }
  }
}
