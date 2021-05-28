package com.limeburger.domain.allergen.mapper;

import com.limeburger.domain.allergen.dto.AllergenCustomerView;
import com.limeburger.domain.allergen.model.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.limeburger.domain.allergen.model.Allergen.AllergenType;

@Mapper
public interface AllergenMapper {

  AllergenMapper INSTANCE = Mappers.getMapper(AllergenMapper.class);

  @Mapping(
      source = "allergenType",
      target = "name",
      qualifiedByName = "AllergenEnumToAllergenEnumValue")
  AllergenCustomerView toAllergenCustomerView(final Allergen allergen);

  @Named("AllergenEnumToAllergenEnumValue")
  static String convertAllergenEnumToAllergenStringValue(final AllergenType target) {
    return target.type;
  }
}
