package com.limeburger.domain.ingredient.mapper;

import com.limeburger.domain.burger.mapper.MapperService;
import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import com.limeburger.domain.ingredient.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.limeburger.domain.ingredient.model.Ingredient.IngredientType;

@Mapper
public interface IngredientMapper extends MapperService {

  IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

  @Mapping(source = "sellingPrice", target = "cost", qualifiedByName = "decimalToString")
  IngredientCustomerView toIngredientCustomerView(final Ingredient ingredient);

  @Mapping(
      source = "ingredientType",
      target = "type",
      qualifiedByName = "IngredientEnumToIngredientStringValue")
  @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "decimalToString")
  @Mapping(source = "purchasePrice", target = "purchasePrice", qualifiedByName = "decimalToString")
  IngredientAdminView toIngredientAdminView(final Ingredient ingredient);

  @Named("IngredientEnumToIngredientStringValue")
  static String convertIngredientEnumToIngredientStringValue(final IngredientType target) {
    return target.type;
  }
}
