package com.mateuszpomorski.primenumbersapi.controllers.advices;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PrimesControllerAdviceTest {

  private final PrimesControllerAdvice advice = new PrimesControllerAdvice();

  @Test
  public void shouldReturnABadRequestResponseForIllegalArgumentException() {
    //given
    ResponseEntity<Object> expectedResponse = new ResponseEntity<>(
        HttpStatus.BAD_REQUEST);
    //when
    ResponseEntity<Object> actualResponse = advice.handleIllegalArgumentException(
        new IllegalArgumentException(""));
    //then
    assertThat(actualResponse, is(expectedResponse));
  }

}