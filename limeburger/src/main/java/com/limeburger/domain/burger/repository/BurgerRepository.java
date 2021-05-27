package com.limeburger.domain.burger.repository;

import com.limeburger.domain.burger.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {}
