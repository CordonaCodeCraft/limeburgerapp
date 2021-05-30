package com.limeburger.domain.burger.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormatException(Exception exception) {
    log.error("Handling Number Format Exception");
    log.error(exception.getMessage());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("NumberFormatExceptionErrorPage");
    modelAndView.addObject("exception", exception);
    return modelAndView;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(NoSuchElementException.class)
  public ModelAndView handleNoSuchElementException(Exception exception) {
    log.error("Handling no such element exception");
    log.error(exception.getMessage());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("NoSuchElementExceptionErrorPage");
    return modelAndView;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
  public ModelAndView handleIncorrectResultSizeDataAccessException(Exception exception) {
    log.error("Handling incorrect result size data access exception");
    log.error(exception.getMessage());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("IncorrectResultSizeExceptionErrorPage");
    return modelAndView;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ModelAndView handleDataIntegrityViolationException(Exception exception) {
    log.error("Handling data integrity violation exception");
    log.error(exception.getMessage());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("DataIntegrityViolationExceptionErrorPage");
    return modelAndView;
  }
}
