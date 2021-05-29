package com.limeburger.domain.burger.controller.admin;

import com.limeburger.domain.burger.dto.admin.BurgerAdminCommand;
import com.limeburger.domain.burger.dto.admin.BurgerAdminView;
import com.limeburger.domain.burger.dto.admin.BurgerAdminViewPagedList;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.Burger;
import com.limeburger.domain.burger.service.BurgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BurgerAdminController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class BurgerAdminController {

  public static final String BASE_URL = "/api/v1/admin";

  private final BurgerService burgerService;

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime admin!";
  }

  @GetMapping("/burgers")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminViewPagedList getAllBurgersAsPage(final Pageable pageable) {

    final Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    final List<BurgerAdminView> burgersAdminView =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerAdminView)
            .collect(Collectors.toList());

    log.info("Returning Page of burgers with admin view");

    return new BurgerAdminViewPagedList(
        burgersAdminView,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @GetMapping("/burgers/id")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminView getBurgerByName(@RequestParam(value = "id") final Long id) {

    Optional<Burger> burger = burgerService.findById(id);

    if (burger.isPresent()) {
      log.info(String.format("Returning burger with ID %d", id));
      return BurgerMapper.INSTANCE.toBurgerAdminView(burger.get());
    } else {
      throw new NoSuchElementException(String.format("Burger with ID %d not found", id));
    }
  }

  @GetMapping("/burgers/name")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminView getBurgerByName(@RequestParam(value = "name") final String name) {

    Optional<Burger> burger = burgerService.findByNameLike("%" + name + "%");

    if (burger.isPresent()) {
      log.info("Returning burger with admin view and name, containing " + name);
      return BurgerMapper.INSTANCE.toBurgerAdminView(burger.get());
    } else {
      throw new NoSuchElementException(String.format("Burger containing \"%s\" not found", name));
    }
  }

  @PostMapping("/burgers/create")
  @ResponseStatus(HttpStatus.CREATED)
  public BurgerAdminView addBurger(
      @Valid @RequestBody final BurgerAdminCommand input, final BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new NumberFormatException("Burger not valid");
    } else {

      final StringBuilder logBuilder = new StringBuilder();
      logBuilder.append(System.lineSeparator());
      logBuilder.append("Creating new burger in database:");
      logBuilder.append(System.lineSeparator());
      logBuilder.append("Type: ").append(input.getBurgerType());
      logBuilder.append(System.lineSeparator());
      logBuilder.append("Name: ").append(input.getName());
      logBuilder.append(System.lineSeparator());
      logBuilder.append("Ingredients Count: ").append(input.getIngredients().size());

      log.info(logBuilder.toString());

      final Optional<Burger> burger = burgerService.addNewBurger(input);
      return BurgerMapper.INSTANCE.toBurgerAdminView(burger.get());
    }
  }
}
