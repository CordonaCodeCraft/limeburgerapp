package com.limeburger.domain.burger.controller.admin;

import com.limeburger.domain.burger.dto.admin.BurgerAdminCommand;
import com.limeburger.domain.burger.dto.admin.BurgerAdminView;
import com.limeburger.domain.burger.dto.admin.BurgerAdminViewPagedList;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AdminBeerController.BASE_URL)
@RequiredArgsConstructor
public class AdminBeerController {

  public static final String BASE_URL = "/api/v1/admin";

  private final BurgerService burgerService;

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime admin!";
  }

  @GetMapping("/burgers/")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminViewPagedList getAllBurgersAsPage(Pageable pageable) {

    Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    List<BurgerAdminView> collect =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerAdminView)
            .collect(Collectors.toList());

    return new BurgerAdminViewPagedList(
        collect,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @GetMapping("/burgers/id/")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminView getBurgerByName(@RequestParam(value = "id") Long id) {
    return BurgerMapper.INSTANCE.toBurgerAdminView(burgerService.findById(id).get());
  }

  @GetMapping("/burgers/name/")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminView getBurgerByName(@RequestParam(value = "name") String name) {
    return BurgerMapper.INSTANCE.toBurgerAdminView(
        burgerService.findByNameLike("%" + name + "%").get());
  }

  @PostMapping("/burgers/add")
  @ResponseStatus(HttpStatus.CREATED)
  public BurgerAdminView addBurger(@Valid @RequestBody BurgerAdminCommand input) {
    Optional<Burger> burger = burgerService.addNewBurger(input);
    return BurgerMapper.INSTANCE.toBurgerAdminView(burger.get());
  }
}
