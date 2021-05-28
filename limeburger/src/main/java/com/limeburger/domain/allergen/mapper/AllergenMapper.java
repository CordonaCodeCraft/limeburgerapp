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

  @Named("enumToEnumValue")
  static String enumToEnumValue(final AllergenType target) {
    return target.type;
  }

  @Mapping(source = "allergenType", target = "name", qualifiedByName = "enumToEnumValue")
  AllergenCustomerView toAllergenCustomerView(final Allergen allergen);
}
