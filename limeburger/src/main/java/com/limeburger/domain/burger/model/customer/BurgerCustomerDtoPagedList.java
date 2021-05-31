package com.limeburger.domain.burger.model.customer;

import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ApiModel(value = "Page of burgers as viewed by a customer")
public class BurgerCustomerDtoPagedList extends PageImpl<BurgerCustomerDto> {

  public BurgerCustomerDtoPagedList(
      final List<BurgerCustomerDto> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BurgerCustomerDtoPagedList(final List<BurgerCustomerDto> content) {
    super(content);
  }
}
