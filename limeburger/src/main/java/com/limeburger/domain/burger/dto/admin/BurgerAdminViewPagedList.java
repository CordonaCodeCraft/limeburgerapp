package com.limeburger.domain.burger.dto.admin;

import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ApiModel(value = "Page of burgers with admin view")
public class BurgerAdminViewPagedList extends PageImpl<BurgerAdminView> {

  public BurgerAdminViewPagedList(
      final List<BurgerAdminView> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BurgerAdminViewPagedList(final List<BurgerAdminView> content) {
    super(content);
  }
}
