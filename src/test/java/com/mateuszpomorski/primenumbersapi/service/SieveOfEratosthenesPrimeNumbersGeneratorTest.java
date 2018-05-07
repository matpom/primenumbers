package com.mateuszpomorski.primenumbersapi.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.ImmutableList;
import com.mateuszpomorski.primenumbersapi.service.generators.SieveOfEratosthenesPrimeNumbersGenerator;
import org.junit.Test;

import java.util.List;

public class SieveOfEratosthenesPrimeNumbersGeneratorTest {

  private final SieveOfEratosthenesPrimeNumbersGenerator generator =
      new SieveOfEratosthenesPrimeNumbersGenerator();

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrownIllegalArgumentExceptionIfTheNumberIsTooBig() {
    //when
    generator.generatePrimes(Integer.MAX_VALUE - 8);
  }

  @Test
  public void shouldReturnAListOfPrimes() {
    //given
    List<Integer> expectedPrimes = ImmutableList.of(2, 3, 5, 7);
    //when
    List<Integer> actualPrimes = generator.generatePrimes(10);
    //then
    assertThat(actualPrimes, is(expectedPrimes));
  }

  @Test
  public void shouldIncludeParameterIfPrime() {
    //given
    int primeAsLimit = 23;
    //when
    List<Integer> actualPrimes = generator.generatePrimes(primeAsLimit);
    //then
    assertThat(actualPrimes, hasItem(primeAsLimit));
  }

}