package com.limeburger.domain.allergen.service;

import com.limeburger.domain.allergen.model.Allergen;

import java.util.List;

import static com.limeburger.domain.allergen.model.Allergen.*;

public interface AllergenService {

  Allergen save(Allergen allergen);

  Allergen getById(Long id);

  Allergen getByType(AllergenType type);


  List<Allergen> saveAll(List<Allergen> allergens);
}
