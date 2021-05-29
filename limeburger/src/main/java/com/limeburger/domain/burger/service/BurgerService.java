package com.limeburger.domain.burger.service;

import com.limeburger.domain.burger.dto.admin.BurgerAdminCommand;
import com.limeburger.domain.burger.model.Burger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BurgerService {

  Burger save(Burger burger);

  Optional<Burger> addNewBurger(BurgerAdminCommand source);

  Page<Burger> findAllBurgers(Pageable pageable);

  Optional<Burger> findById(Long id);

  Optional<Burger> findByName(String name);

  Optional<Burger> findByNameLike(String name);

  List<Burger> saveAll(List<Burger> burgers);

  Burger getRandomBurger();
}
