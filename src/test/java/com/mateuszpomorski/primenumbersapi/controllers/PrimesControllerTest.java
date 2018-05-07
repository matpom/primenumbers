package com.mateuszpomorski.primenumbersapi.controllers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.mateuszpomorski.primenumbersapi.model.PrimesResponse;
import com.mateuszpomorski.primenumbersapi.service.PrimeNumbersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PrimesControllerTest {

  @Mock
  private PrimeNumbersService primeNumbersService;

  @InjectMocks
  private PrimesController primesController;

  @Test
  public void shouldReturnPrimesResponseWithValuesFromService() {
    //given
    int limit = 10;
    List<Integer> expectedPrimes = ImmutableList.of(2, 3, 5, 7);
    PrimesResponse expectedResponse = new PrimesResponse(limit, expectedPrimes);
    when(primeNumbersService.generatePrimeNumbers(limit)).thenReturn(expectedPrimes);
    //when
    PrimesResponse actualPrimesResponse = primesController.generatePrimes(limit);
    //then
    assertThat(actualPrimesResponse, is(expectedResponse));
  }

}