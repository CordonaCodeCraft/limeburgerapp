package com.limeburger.domain.allergen.mapper;

import com.limeburger.domain.allergen.model.AllergenCustomerDto;
import com.limeburger.domain.allergen.entity.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.limeburger.domain.allergen.entity.Allergen.AllergenType;

@Mapper
public interface AllergenMapper {

  AllergenMapper INSTANCE = Mappers.getMapper(AllergenMapper.class);

  @Mapping(
      source = "allergenType",
      target = "name",
      qualifiedByName = "AllergenEnumToAllergenStringValue")
  AllergenCustomerDto toAllergenCustomerDto(final Allergen source);

  @Named("AllergenEnumToAllergenStringValue")
  static String convertAllergenEnumToAllergenStringValue(final AllergenType source) {
    return source.type;
  }
}
