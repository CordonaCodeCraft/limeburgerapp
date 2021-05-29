package com.limeburger.domain.burger.controller.customer;

import com.limeburger.domain.burger.dto.customer.BurgerCustomerView;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerViewPagedList;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CustomerBeerController.BASE_URL)
@RequiredArgsConstructor
public class CustomerBeerController {

  public static final String BASE_URL = "/api/v1/customer";

  private final BurgerService burgerService;

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime customer!";
  }

  @GetMapping("/burgers/")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerViewPagedList getAllBurgersAsPage(Pageable pageable) {

    Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    List<BurgerCustomerView> collect =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerCustomerView)
            .collect(Collectors.toList());

    return new BurgerCustomerViewPagedList(
        collect,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @GetMapping("/burgers/name/")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerView getBurgerByName(@RequestParam("name") String name) {
    return BurgerMapper.INSTANCE.toBurgerCustomerView(
        burgerService.findByNameLike("%" + name + "%").get());
  }

  @GetMapping("/burgers/random")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerView getRandomBurger() {
    return BurgerMapper.INSTANCE.toBurgerCustomerView(burgerService.getRandomBurger());
  }
}
