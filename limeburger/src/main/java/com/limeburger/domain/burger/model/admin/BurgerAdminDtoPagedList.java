package com.limeburger.domain.burger.model.admin;

import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ApiModel(value = "Page of burgers as viewed by an admin user")
public class BurgerAdminDtoPagedList extends PageImpl<BurgerAdminDto> {

  public BurgerAdminDtoPagedList(
      final List<BurgerAdminDto> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BurgerAdminDtoPagedList(final List<BurgerAdminDto> content) {
    super(content);
  }
}
