package com.limeburger.domain.ingredient.mapper;

import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.limeburger.domain.ingredient.model.Ingredient.IngredientType;

@Mapper
public interface IngredientMapper extends MapperUtils {

  IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

  @Named("IngredientEnumToIngredientEnumValue")
  static String enumToEnumValue(final IngredientType target) {
    return target.type;
  }

  @Mapping(source = "sellingPrice", target = "cost", qualifiedByName = "decimalToString")
  IngredientCustomerView toIngredientCustomerView(final Ingredient ingredient);

  @Mapping(
      source = "ingredientType",
      target = "type",
      qualifiedByName = "IngredientEnumToIngredientEnumValue")
  @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "decimalToString")
  @Mapping(source = "purchasePrice", target = "purchasePrice", qualifiedByName = "decimalToString")
  IngredientAdminView toIngredientAdminView(final Ingredient ingredient);
}
