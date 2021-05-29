package com.limeburger.domain.burger.dto.admin;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BurgerAdminViewPagedList extends PageImpl<BurgerAdminView> {

    public BurgerAdminViewPagedList(
            final List<BurgerAdminView> content, final Pageable pageable, final long total) {
        super(content, pageable, total);
    }

    public BurgerAdminViewPagedList(final List<BurgerAdminView> content) {
        super(content);
    }
}