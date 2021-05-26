package com.limeburger.domain.allergen;

import com.limeburger.domain.BaseEntity;
import com.limeburger.domain.ingredient.model.Ingredient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Allergen")
@Table(name = "allergens")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Allergen extends BaseEntity {

  @NotNull(message = "Allergen type can not be empty")
  @Enumerated(value = EnumType.STRING)
  private AllergenType allergenType;

  @Column(unique = true)
  @NotBlank(message = "Description can not be empty")
  @Length(
          min = 10,
          max = 255,
          message = "Description must be between 10 (inclusive) and 255 (inclusive) characters")
  private String description;

  @NotBlank(message = "Allergen image URL can not be empty")
  private String imageUrl;

  @NotNull(message = "Danger index can not be empty")
  @Range(
          min = 1,
          max = 10,
          message = "Danger index must be between 1 (inclusive) and 10 (inclusive) points")
  private Integer dangerIndex;

  @ManyToMany(mappedBy = "allergens")
  private Set<Ingredient> ingredients = new HashSet<>();

  enum AllergenType {
    CRUSTACEAN("Crustaceans"),
    EGG("Eggs"),
    FISH("Fish"),
    PEANUT("Peanuts"),
    SOYBEAN("Soybeans"),
    NUT("Nuts"),
    CELERY("Celery"),
    MUSTARD("Mustard"),
    SESAME("Sesame seeds"),
    SULPHUR("Sulphur dioxide and sulphides"),
    LUPIN("Lupin"),
    MOLLUSC("Molluscs"),
    MILK("Milk");

    public final String type;

    AllergenType(String name) {
      this.type = name;
    }
  }
}
