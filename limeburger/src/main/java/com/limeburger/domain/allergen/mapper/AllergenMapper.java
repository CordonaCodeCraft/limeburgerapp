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
      qualifiedByName = "AllergenEnumToAllergenStringValue")
  AllergenCustomerView toAllergenCustomerView(final Allergen source);

  @Named("AllergenEnumToAllergenStringValue")
  static String convertAllergenEnumToAllergenStringValue(final AllergenType source) {
    return source.type;
  }
}
