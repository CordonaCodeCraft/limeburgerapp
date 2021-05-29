package com.limeburger.domain.allergen.service.impl;

import com.limeburger.domain.allergen.model.Allergen;
import com.limeburger.domain.allergen.repository.AllergenRepository;
import com.limeburger.domain.allergen.service.AllergenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllergenServiceImpl implements AllergenService {

  private final AllergenRepository allergenRepository;

  @Override
  public Allergen save(final Allergen allergen) {
    log.info(String.format("Saved \"%s\" allergen in database", allergen.getAllergenType().type));
    return allergenRepository.save(allergen);
  }

  @Override
  public Allergen getById(final Long id) {
    log.info(String.format("Fetched allergen with id %d from database", id));
    return allergenRepository.getAllergenById(id);
  }

  @Override
  public Allergen getByType(final Allergen.AllergenType type) {
    return allergenRepository.getAllergenByType(type);
  }

  @Override
  public List<Allergen> saveAll(final List<Allergen> allergens) {
    allergens.forEach(
        a ->
            log.info(String.format("Saved \"%s\" allergen in database", a.getAllergenType().type)));
    log.info(String.format("Saved a total of %d allergens in database", allergens.size()));
    return allergenRepository.saveAll(allergens);
  }
}
