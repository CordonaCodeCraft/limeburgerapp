package com.limeburger.domain.allergen.service;

import com.limeburger.domain.allergen.entity.Allergen;

import java.util.List;

import static com.limeburger.domain.allergen.entity.Allergen.AllergenType;

public interface AllergenService {

  Allergen save(final Allergen allergen);

  Allergen getById(final Long id);

  Allergen getByType(final AllergenType type);

  List<Allergen> saveAll(final List<Allergen> allergens);
}
