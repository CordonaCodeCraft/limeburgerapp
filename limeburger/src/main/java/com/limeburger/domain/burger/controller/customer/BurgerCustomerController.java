package com.limeburger.domain.burger.controller.customer;

import com.limeburger.domain.burger.dto.customer.BurgerCustomerCommand;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerComposed;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerView;
import com.limeburger.domain.burger.dto.customer.BurgerCustomerViewPagedList;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.service.BurgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BurgerCustomerController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class BurgerCustomerController {

  public static final String BASE_URL = "/api/v1/customer";

  private final BurgerService burgerService;

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime customer!";
  }

  @GetMapping("/burgers")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerViewPagedList getAllBurgersAsPage(final Pageable pageable) {

    final Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    final List<BurgerCustomerView> collect =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerCustomerView)
            .collect(Collectors.toList());

    log.info("Returning Page of burgers with customer view");

    return new BurgerCustomerViewPagedList(
        collect,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @GetMapping("/burgers/name")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerView getBurgerByName(@RequestParam("name") final String name) {

    log.info("Returning burger with customer view and name, containing " + name);

    return BurgerMapper.INSTANCE.toBurgerCustomerView(
        burgerService.findByNameLike("%" + name + "%").get());
  }

  @GetMapping("/burgers/random")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerView getRandomBurger() {

    log.info("Returning random burger");

    return BurgerMapper.INSTANCE.toBurgerCustomerView(burgerService.getRandomBurger());
  }

  @PostMapping("/burgers/compose")
  @ResponseStatus(HttpStatus.CREATED)
  public BurgerCustomerComposed addBurger(@Valid @RequestBody final BurgerCustomerCommand input) {

    log.info(
        String.format(
            "Returning \"%s\" burger, composed by customer with %d ingredients",
            input.getName(), input.getIngredients().size()));

    return BurgerMapper.INSTANCE.toBurgerCustomerComposed(input);
  }
}
