package com.limeburger.domain.allergen.service;

import com.limeburger.domain.allergen.model.Allergen;

import java.util.List;

public interface AllergenService {

  Allergen save(Allergen allergen);

  Allergen getById(Long id);

  List<Allergen> saveAll(List<Allergen> allergens);
}
