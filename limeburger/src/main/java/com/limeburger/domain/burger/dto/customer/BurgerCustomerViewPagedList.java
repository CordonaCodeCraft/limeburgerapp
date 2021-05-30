package com.limeburger.domain.burger.dto.customer;

import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ApiModel(value = "Page of burgers with customer view")
public class BurgerCustomerViewPagedList extends PageImpl<BurgerCustomerView> {

  public BurgerCustomerViewPagedList(
      final List<BurgerCustomerView> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BurgerCustomerViewPagedList(final List<BurgerCustomerView> content) {
    super(content);
  }
}
