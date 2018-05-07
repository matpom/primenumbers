package com.mateuszpomorski.primenumbersapi.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class SieveOfEratosthenesPrimeNumbersGenerator implements PrimeNumbersGenerator {

  /**
   * The maximum size of array to allocate.
   * Some VMs reserve some header words in an array.
   * Attempts to allocate larger arrays may result in
   * OutOfMemoryError: Requested array size exceeds VM limit
   */
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  /**
   * Generates prime numbers using a Sieve of Eratosthenes algorithm.
   *
   * @param limit the value up to which prime numbers are generated
   * @return the list of prime numbers
   */
  @Override
  public List<Integer> generatePrimes(int limit) {
    if (limit < 0 || limit > MAX_ARRAY_SIZE - 1) {
      throw new IllegalArgumentException("Attempt to generate primes for: " + limit);
    }
    boolean[] prime = new boolean[limit + 1];
    Arrays.fill(prime, true);
    for (int p = 2; p * p <= limit; p++) {
      if (prime[p]) {
        for (int i = p * 2; i <= limit; i += p) {
          prime[i] = false;
          if (i + p < 0) {
            // integer limit would be crossed
            break;
          }
        }
      }
    }
    List<Integer> primeNumbers = new LinkedList<>();
    for (int i = 2; i <= limit; i++) {
      if (prime[i]) {
        primeNumbers.add(i);
      }
    }
    return primeNumbers;
  }
}
