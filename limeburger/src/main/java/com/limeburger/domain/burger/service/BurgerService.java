package com.limeburger.domain.burger.service;

import com.limeburger.domain.burger.model.admin.CreateBurgerAdminRequest;
import com.limeburger.domain.burger.entity.Burger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BurgerService {

  Burger save(final Burger burger);

  Optional<Burger> addNewBurger(final CreateBurgerAdminRequest source);

  Page<Burger> findAllBurgers(final Pageable pageable);

  Optional<Burger> findById(final Long id);

  Optional<Burger> findByName(final String name);

  Optional<Burger> findByNameLike(final String name);

  List<Burger> saveAll(final List<Burger> burgers);

  Burger getRandomBurger();
}
