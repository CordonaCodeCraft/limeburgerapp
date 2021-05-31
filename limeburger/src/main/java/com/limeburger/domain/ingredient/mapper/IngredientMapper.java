package com.limeburger.domain.ingredient.mapper;

import com.limeburger.domain.burger.mapper.MapperService;
import com.limeburger.domain.ingredient.entity.Ingredient;
import com.limeburger.domain.ingredient.model.IngredientAdminDto;
import com.limeburger.domain.ingredient.model.IngredientCustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.limeburger.domain.ingredient.entity.Ingredient.IngredientType;

@Mapper
public interface IngredientMapper extends MapperService {

  IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

  @Mapping(source = "sellingPrice", target = "cost", qualifiedByName = "decimalToString")
  IngredientCustomerDto toIngredientCustomerDto(final Ingredient source);

  @Mapping(
      source = "ingredientType",
      target = "type",
      qualifiedByName = "IngredientEnumToIngredientStringValue")
  @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "decimalToString")
  @Mapping(source = "purchasePrice", target = "purchasePrice", qualifiedByName = "decimalToString")
  IngredientAdminDto toIngredientAdminDto(final Ingredient source);

  @Named("IngredientEnumToIngredientStringValue")
  static String convertIngredientEnumToIngredientStringValue(final IngredientType source) {
    return source.type;
  }
}
