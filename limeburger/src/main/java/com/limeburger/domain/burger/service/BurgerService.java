package com.limeburger.domain.burger.service;

import com.limeburger.domain.burger.model.Burger;

import java.util.List;

public interface BurgerService {

    Burger save(Burger burger);

    List<Burger> saveAll(List<Burger> burgers);
}
