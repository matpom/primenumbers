package com.mateuszpomorski.primenumbersapi.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.mateuszpomorski.primenumbersapi.service.generators.SieveOfEratosthenesPrimeNumbersGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CachingPrimeNumbersServiceTest {

  private static final int SAMPLE_LIMIT = 12;
  private static final ImmutableList<Integer> SAMPLE_PRIMES = ImmutableList.of(2, 3, 5, 7, 11);
  private static final int LIMIT_LOWER_THAN_CACHED = 7;
  private static final ImmutableList<Integer> PRIMES_FOR_LOWER_LIMIT = ImmutableList.of(2, 3, 5, 7);
  private static final int LIMIT_HIGHER_THAN_CACHED = 15;
  private static final ImmutableList<Integer> PRIMES_FOR_HIGHER_LIMIT =
      ImmutableList.of(2, 3, 5, 7, 11, 13);

  @Mock
  private SieveOfEratosthenesPrimeNumbersGenerator primeNumbersGeneratorMock;

  private PrimeNumbersService primeNumbersService;

  @Before
  public void setUp() {
    when(primeNumbersGeneratorMock.generatePrimes(SAMPLE_LIMIT))
        .thenReturn(SAMPLE_PRIMES);
    when(primeNumbersGeneratorMock.generatePrimes(LIMIT_HIGHER_THAN_CACHED))
        .thenReturn(PRIMES_FOR_HIGHER_LIMIT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenCalledWithNegativeLimit() {
    //given
    primeNumbersServiceHasCachingDisabled();
    //then
    primeNumbersService.generatePrimeNumbers(-1);
    //then
    verify(primeNumbersGeneratorMock, never()).generatePrimes(any());
  }

  @Test
  public void shouldReturnResultFromGenerator() {
    //given
    primeNumbersServiceHasCachingDisabled();
    //when
    List<Integer> actualPrimes = primeNumbersService.generatePrimeNumbers(SAMPLE_LIMIT);
    //then
    assertThat(actualPrimes, is(SAMPLE_PRIMES));
    verify(primeNumbersGeneratorMock).generatePrimes(SAMPLE_LIMIT);
  }

  @Test
  public void shouldNotCallGeneratorTwiceForTheSameLimitWhenCachingIsEnabled() {
    //given
    primeNumbersServiceHasCachingEnabled();
    //when
    primeNumbersService.generatePrimeNumbers(SAMPLE_LIMIT);
    List<Integer> actualPrimes = primeNumbersService.generatePrimeNumbers(SAMPLE_LIMIT);
    //then
    assertThat(actualPrimes, is(SAMPLE_PRIMES));
    verify(primeNumbersGeneratorMock).generatePrimes(SAMPLE_LIMIT);
  }

  @Test
  public void shouldNotCallGeneratorTwiceForTheLimitLowerThanAlreadyCachedWhenCachingIsEnabled() {
    //given
    primeNumbersServiceHasCachingEnabled();
    //when
    primeNumbersService.generatePrimeNumbers(SAMPLE_LIMIT);
    List<Integer> actualPrimes = primeNumbersService.generatePrimeNumbers(LIMIT_LOWER_THAN_CACHED);
    //then
    assertThat(actualPrimes, is(PRIMES_FOR_LOWER_LIMIT));
    verify(primeNumbersGeneratorMock).generatePrimes(SAMPLE_LIMIT);
    verify(primeNumbersGeneratorMock, never()).generatePrimes(LIMIT_LOWER_THAN_CACHED);
  }

  @Test
  public void shouldCallGeneratorIfLimitIsHigherThanCachedWhenCachingIsEnabled() {
    //given
    primeNumbersServiceHasCachingEnabled();
    //when
    primeNumbersService.generatePrimeNumbers(SAMPLE_LIMIT);
    List<Integer> actualPrimes = primeNumbersService.generatePrimeNumbers(LIMIT_HIGHER_THAN_CACHED);
    //then
    assertThat(actualPrimes, is(PRIMES_FOR_HIGHER_LIMIT));
    verify(primeNumbersGeneratorMock).generatePrimes(SAMPLE_LIMIT);
    verify(primeNumbersGeneratorMock).generatePrimes(LIMIT_HIGHER_THAN_CACHED);
  }

  private void primeNumbersServiceHasCachingDisabled() {
    primeNumbersService = new CachingPrimeNumbersService(false, primeNumbersGeneratorMock);
  }

  private void primeNumbersServiceHasCachingEnabled() {
    primeNumbersService = new CachingPrimeNumbersService(true, primeNumbersGeneratorMock);
  }

}