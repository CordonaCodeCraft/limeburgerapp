package com.limeburger.domain.burger.repository;

import com.limeburger.domain.burger.model.Burger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {

  @Query(
      "select b from Burger as b left join fetch b.ingredients as i left join fetch i.allergens where b.id = :id")
  Optional<Burger> findBurgerById(@Param("id") Long id);

  @Query(
      "select b from Burger as b left join fetch b.ingredients as i left join fetch i.allergens where b.name like :name")
  Optional<Burger> findBurgerByName(@Param("name") String name);

  @Query(
      "select b from Burger as b left join fetch b.ingredients as i left join fetch i.allergens where b.name like :name")
  Optional<Burger> findBurgerByNameLike(@Param("name") String name);

  @Query("select b from Burger as b order by b.id asc")
  Page<Burger> findAllBurgers(Pageable pageable);

  @Query("select b.id from Burger as b")
  List<Long> getAllIds();
}
