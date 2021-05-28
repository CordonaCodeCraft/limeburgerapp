package com.limeburger.domain.ingredient.mapper;

import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.ingredient.dto.IngredientAdminView;
import com.limeburger.domain.ingredient.dto.IngredientCustomerView;
import com.limeburger.domain.ingredient.model.Ingredient;
import com.limeburger.domain.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.limeburger.domain.ingredient.model.Ingredient.*;

@Mapper
public interface IngredientMapper extends MapperUtils {

  IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

  @Mapping(source = "sellingPrice", target = "cost", qualifiedByName = "decimalToString")
  IngredientCustomerView toIngredientCustomerView(Ingredient ingredient);

  @Mapping(source = "ingredientType", target = "type", qualifiedByName = "IngredientEnumToIngredientEnumValue")
  @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "decimalToString")
  @Mapping(source = "purchasePrice", target = "purchasePrice", qualifiedByName = "decimalToString")
//  @Mapping(target = "burgersContainingIngredient", source = "burgers", qualifiedByName = "burgersSetToBurgerNamesList")
  IngredientAdminView toIngredientAdminView(Ingredient ingredient);

  @Named("IngredientEnumToIngredientEnumValue")
  static String enumToEnumValue(IngredientType target) {
    return target.type;
  }

}
