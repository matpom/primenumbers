package com.mateuszpomorski.primenumbersapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PrimesResponse {

  @JsonProperty("Initial")
  private final int initial;
  @JsonProperty("Primes")
  private final List<Integer> primes;

  public PrimesResponse(int initial, List<Integer> primes) {
    this.initial = initial;
    this.primes = primes;
  }

  public int getInitial() {
    return initial;
  }

  public List<Integer> getPrimes() {
    return primes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrimesResponse that = (PrimesResponse) o;
    return initial == that.initial &&
        Objects.equals(primes, that.primes);
  }

  @Override
  public int hashCode() {

    return Objects.hash(initial, primes);
  }
}
