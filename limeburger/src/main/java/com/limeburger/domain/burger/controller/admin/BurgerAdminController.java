package com.limeburger.domain.burger.controller.admin;

import com.limeburger.domain.burger.entity.Burger;
import com.limeburger.domain.burger.mapper.BurgerMapper;
import com.limeburger.domain.burger.model.admin.BurgerAdminDto;
import com.limeburger.domain.burger.model.admin.BurgerAdminDtoPagedList;
import com.limeburger.domain.burger.model.admin.CreateBurgerAdminRequest;
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

@Api(tags = {"Admin API"})
@RestController
@RequestMapping(BurgerAdminController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class BurgerAdminController {

  public static final String BASE_URL = "/api/v1/admin";

  private final BurgerService burgerService;

  @ApiOperation(value = "Greets the admin with a friendly message")
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String sayHi() {
    return "Hello lime admin!";
  }

  @ApiOperation(
      value = "Displays a paged list of all burgers",
      notes = "The burgers are being displayed in admin view")
  @GetMapping("/burgers")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminDtoPagedList getAllBurgersAsPage(final Pageable pageable) {

    final Page<Burger> pagedBurgers = burgerService.findAllBurgers(pageable);

    final List<BurgerAdminDto> burgersAdminDto =
        pagedBurgers.stream()
            .map(BurgerMapper.INSTANCE::toBurgerAdminDto)
            .collect(Collectors.toList());

    log.info("Returning Page of burgers with admin view");

    return new BurgerAdminDtoPagedList(
        burgersAdminDto,
        PageRequest.of(
            pagedBurgers.getPageable().getPageNumber(), pagedBurgers.getPageable().getPageSize()),
        pagedBurgers.getTotalElements());
  }

  @ApiOperation(
      value = "Returns a burger, queried by ID",
      notes =
          "Only the admins can query a burger by ID - the customers do not have access to this property\n"
              + "The burger is being displayed in admin view.\n"
              + "In case of a wrong query, the corresponding \"NoSuchElementException\" is being handled with a basic HTML page, describing the error. Offensively\"")
  @GetMapping("/burgers/id")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminDto getBurgerByName(
      @ApiParam(value = "The burger id, queried by the user") @RequestParam(value = "id")
          final Long id) {

    Optional<Burger> burger = burgerService.findById(id);

    if (burger.isPresent()) {
      log.info(String.format("Returning burger with ID %d", id));
      return BurgerMapper.INSTANCE.toBurgerAdminDto(burger.get());
    } else {
      throw new NoSuchElementException(String.format("Burger with ID %d not found", id));
    }
  }

  @ApiOperation(
      value = "Returns a burger, queried by name",
      notes =
          "The burger is being displayed in admin view.\n"
              + "Burger will be returned even in case of a partial match ( \"Lim\" for \"Lime burger\" ). \n"
              + "If the query matches multiple burgers - for the demo the corresponding exception ( \"IncorrectResultSizeDataAccessException\n"
              + "\" ) is being handled with a basic HTML page, describing the error.\n"
              + "In case of a wrong query, the corresponding \"NoSuchElementException\" is being handled with a basic HTML page, describing the error. Offensively\"")
  @GetMapping("/burgers/name")
  @ResponseStatus(HttpStatus.OK)
  public BurgerAdminDto getBurgerByName(
      @ApiParam(value = "The burger name or part of the burger's name, queried by the user")
          @RequestParam(value = "name")
          final String name) {

    Optional<Burger> burger = burgerService.findByNameLike("%" + name + "%");

    if (burger.isPresent()) {
      log.info("Returning burger with admin view and name, containing " + name);
      return BurgerMapper.INSTANCE.toBurgerAdminDto(burger.get());
    } else {
      throw new NoSuchElementException(String.format("Burger containing \"%s\" not found", name));
    }
  }

  @ApiOperation(
      value = "Persists a new burger in the database",
      notes =
          "Only the admins can create a new burger in the database. \n"
              + "In contrast - the customers can compose a burger with a preferred list of ingredients")
  @PostMapping("/burgers/create")
  @ResponseStatus(HttpStatus.CREATED)
  public BurgerAdminDto addBurger(
      @ApiParam(
              value =
                  "Command object, containing the necessary information for creating a new burger")
          @Valid
          @RequestBody
          final CreateBurgerAdminRequest input,
      final BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new NumberFormatException("Burger not valid");
    } else {

      log.info("Adding new burger in database");

      final Optional<Burger> burger = burgerService.addNewBurger(input);

      return BurgerMapper.INSTANCE.toBurgerAdminDto(burger.get());
    }
  }
}
