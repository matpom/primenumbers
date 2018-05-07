package com.mateuszpomorski.primenumbersapi.service;

import com.mateuszpomorski.primenumbersapi.service.generators.PrimeNumbersGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CachingPrimeNumbersService implements PrimeNumbersService {

  private final boolean cachingEnabled;
  private final PrimeNumbersGenerator primeNumbersGenerator;

  private List<Integer> cachedPrimes = Collections.emptyList();
  private int cacheCalculatedForLimit = 0;

  public CachingPrimeNumbersService(@Value("${primes.caching.enabled}") boolean cachingEnabled,
                                    PrimeNumbersGenerator primeNumbersGenerator) {
    this.cachingEnabled = cachingEnabled;
    this.primeNumbersGenerator = primeNumbersGenerator;
  }

  /**
   * Generates and caches the prime numbers for a requested limit.
   *
   * @param limit the value up to which prime numbers are generated
   * @return the list of prime numbers
   */
  @Override
  public List<Integer> generatePrimeNumbers(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("Attempt to generate primes for: " + limit);
    }
    if (cachingEnabled) {
      synchronized (this) {
        refreshCacheIfNeeded(limit);
        return getCachedPrimesUpToLimit(limit);
      }
    }
    return primeNumbersGenerator.generatePrimes(limit);
  }

  private void refreshCacheIfNeeded(int limit) {
    if (!primesAlreadyCalculated(limit)) {
      cachedPrimes = primeNumbersGenerator.generatePrimes(limit);
      cacheCalculatedForLimit = limit;
    }
  }

  private boolean primesAlreadyCalculated(int limit) {
    return !cachedPrimes.isEmpty() && cacheCalculatedForLimit >= limit;
  }

  private List<Integer> getCachedPrimesUpToLimit(int limit) {
    return cachedPrimes.stream()
        .filter(prime -> prime <= limit)
        .collect(Collectors.toList());
  }
}