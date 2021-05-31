package com.limeburger.domain.burger.controller.customer;

import com.limeburger.domain.burger.entity.Burger;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.customer.BurgerComposedDto;
import com.limeburger.domain.burger.model.customer.BurgerCustomerDto;
import com.limeburger.domain.burger.model.customer.BurgerCustomerDtoPagedList;
import com.limeburger.domain.burger.model.customer.ComposeBurgerCustomerRequest;
import com.limeburger.domain.burger.service.BurgerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = {"Customer API"})
@RestController
@RequestMapping(BurgerCustomerController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class BurgerCustomerController {

  public static final String BASE_URL = "/api/v1/customer";

  private final BurgerService burgerService;

  @ApiOperation(value = "Greets the customer with a friendly message")
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime customer!";
  }

  @ApiOperation(
      value = "Displays a paged list of all burgers",
      notes = "The burgers are being displayed in customer view")
  @GetMapping("/burgers")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerDtoPagedList getAllBurgersAsPage(final Pageable pageable) {

    final Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    final List<BurgerCustomerDto> burgersCustomerDto =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerCustomerDto)
            .collect(Collectors.toList());

    log.info("Returning Page of burgers with customer view");

    return new BurgerCustomerDtoPagedList(
        burgersCustomerDto,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @ApiOperation(
      value = "Returns a burger, queried by name",
      notes =
          "The burger is being displayed in customer view.\n"
              + "Burger will be returned even in case of a partial match ( \"Lim\" for \"Lime burger\" ). \n"
              + "If the query matches multiple burgers - for the demo the corresponding exception ( \"IncorrectResultSizeDataAccessException\n"
              + "\" ) is being handled with a basic HTML page, describing the error.\n"
              + "In case of a wrong query, the corresponding \"NoSuchElementException\" is being handled with a basic HTML page, describing the error. Offensively\"")
  @GetMapping("/burgers/name")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerDto getBurgerByName(
      @ApiParam(value = "The burger name or part of the burger's name, queried by the user")
          @RequestParam("name")
          final String name) {

    Optional<Burger> burger = burgerService.findByNameLike("%" + name + "%");

    if (burger.isPresent()) {
      log.info("Returning burger with customer view and name, containing " + name);
      return BurgerMapper.INSTANCE.toBurgerCustomerDto(burger.get());
    } else {
      throw new NoSuchElementException(String.format("Burger containing \"%s\" not found", name));
    }
  }

  @ApiOperation(
      value = "Returns a random burger",
      notes =
          "Only the customers can query a random burger.\n"
              + "The assumption is that there is no use case where an admin user would want to randomly access a burger")
  @GetMapping("/burgers/random")
  @ResponseStatus(HttpStatus.OK)
  public BurgerCustomerDto getRandomBurger() {

    log.info("Returning random burger");

    return BurgerMapper.INSTANCE.toBurgerCustomerDto(burgerService.getRandomBurger());
  }

  @ApiOperation(
      value = "Composes a burger with ingredients, preferred by the customer",
      notes =
          "The composed burger is not being persisted in the database.\n"
              + "the method simply returns a JSON representation of the composed burger in customer view")
  @GetMapping("/burgers/compose")
  @ResponseStatus(HttpStatus.CREATED)
  public BurgerComposedDto addBurger(
      @ApiParam(
              value =
                  "Command object, containing the necessary information for composing a new burger")
          @Valid
          @RequestBody
          final ComposeBurgerCustomerRequest input,
      final BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new NumberFormatException("Burger not valid");
    } else {
      log.info(
          String.format(
              "Returning \"%s\" burger, composed by customer with %d ingredients",
              input.getName(), input.getIngredients().size()));

      return BurgerMapper.INSTANCE.toBurgerComposedDto(input);
    }
  }
}
