package com.limeburger.domain.ingredient.model;

import com.limeburger.domain.BaseEntity;
import com.limeburger.domain.allergen.Allergen;
import com.limeburger.domain.burger.model.Burger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "Ingredient")
@Table(name = "ingredients")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
          min = 25,
          max = 255,
          message = "Description must be between 10 (inclusive) and 255 (inclusive) characters")
  private String description;

  @Column(unique = true)
  @NotBlank(message = "Ingredient image URL can not be empty")
  private String imageUrl;

  @NotNull(message = "Ingredient weight can not be empty")
  @Positive(message = "Grammage must be positive value")
  @Range(
      min = 10,
      max = 100,
      message = "Ingredient grammage must be between 10 (inclusive) and 100 (inclusive) grams")
  private Integer grammage;

  @NotNull(message = "Calories can not be empty")
  @Positive(message = "Calories value must be positive real digit")
  @DecimalMin(value = "1.00", message = "Calorie value can not be lesser than 1.00")
  @DecimalMax(value = "250.00", message = "Calorie value can not be bigger than 250.00")
  private BigDecimal calories;

  @NotNull(message = "Price can not be empty")
  @Positive(message = "Price value must be positive real digit")
  @DecimalMin(value = "0.50", message = "Price value can not be lesser than 0.50")
  @DecimalMax(value = "5.00", message = "Price value can not be bigger than 5.00")
  private BigDecimal price;

  @NotNull(message = "Cost can not be empty")
  @Positive(message = "Price value must be positive real digit")
  @DecimalMin(value = "0.50", message = "Cost value can not be lesser than 0.50")
  @DecimalMax(value = "2.00", message = "Cost value can not be bigger than 5.00")
  private BigDecimal cost;

  @ManyToMany(mappedBy = "ingredients")
  private Set<Burger> burgers;

  @ManyToMany(cascade = {PERSIST, MERGE})
  @JoinTable(
      name = "ingredients_allergens",
      joinColumns = @JoinColumn(name = "ingredient_id"),
      inverseJoinColumns = @JoinColumn(name = "allergen_id"))
  private Set<Allergen> allergens = new HashSet<>();

  enum IngredientType {
    BREAD("Bread"),
    MEAT("Meat"),
    VEGETABLE("Vegetable"),
    SAUCE("Sauce"),
    ADD_ON("Add-on"),
    ;

    public final String type;

    IngredientType(String name) {
      this.type = name;
    }
  }
}
