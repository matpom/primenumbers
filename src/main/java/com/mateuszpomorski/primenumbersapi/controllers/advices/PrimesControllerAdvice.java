package com.mateuszpomorski.primenumbersapi.controllers.advices;

import com.mateuszpomorski.primenumbersapi.controllers.PrimesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = PrimesController.class)
public class PrimesControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(PrimesControllerAdvice.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(Exception ex) {
    logger.error("Prime generation requested with a bad parameter", ex);
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}