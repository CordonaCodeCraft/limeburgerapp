package com.limeburger.domain.ingredient.repository;

import com.limeburger.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

  @Query(
      "select i from Ingredient as i left join fetch i.allergens left join fetch i.burgers where i.id = :id")
  Ingredient getIngredientById(@Param("id") final Long id);

  Optional<Ingredient> getByName(final String name);
}
