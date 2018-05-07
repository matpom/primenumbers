package com.mateuszpomorski.primenumbersapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
}
