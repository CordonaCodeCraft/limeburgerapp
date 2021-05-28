package com.limeburger.domain.allergen.repository;

import com.limeburger.domain.allergen.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, Long> {

  @Query("select a from Allergen as a left join fetch a.ingredients where a.id = :id")
  Allergen getAllergenById(@Param("id") Long id);

  //todo: consider is it necessary to fetch the whole entity graph
  @Query(
      "select a from Allergen as a left join fetch a.ingredients as i left join fetch i.allergens left join fetch i.burgers where a.allergenType = :type")
  Allergen getAllergenByType(@Param("type") Allergen.AllergenType type);
}
