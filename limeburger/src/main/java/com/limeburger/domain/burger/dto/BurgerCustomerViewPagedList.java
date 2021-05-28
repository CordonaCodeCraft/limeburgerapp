package com.limeburger.domain.burger.dto;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BurgerCustomerViewPagedList extends PageImpl<BurgerCustomerView> {

  public BurgerCustomerViewPagedList(
      final List<BurgerCustomerView> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BurgerCustomerViewPagedList(final List<BurgerCustomerView> content) {
    super(content);
  }
}
