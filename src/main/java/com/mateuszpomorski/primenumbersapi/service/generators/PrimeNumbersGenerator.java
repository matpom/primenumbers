package com.mateuszpomorski.primenumbersapi.service.generators;

import java.util.List;

public interface PrimeNumbersGenerator {

  /**
   * Generates a list of prime numbers up to and including a number provided.
   *
   * @param limit the value up to which prime numbers are generated
   * @return the list of prime numbers
   */
  List<Integer> generatePrimes(int limit);
}
