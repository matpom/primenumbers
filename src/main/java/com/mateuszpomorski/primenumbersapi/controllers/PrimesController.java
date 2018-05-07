package com.mateuszpomorski.primenumbersapi.controllers;

import com.mateuszpomorski.primenumbersapi.model.PrimesResponse;
import com.mateuszpomorski.primenumbersapi.service.PrimeNumbersGenerator;
import com.mateuszpomorski.primenumbersapi.service.SieveOfEratosthenesPrimeNumbersGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primes")
public class PrimesController {

  private final PrimeNumbersGenerator generator;

  public PrimesController(SieveOfEratosthenesPrimeNumbersGenerator generator) {
    this.generator = generator;
  }

  @GetMapping("/{limit}")
  public PrimesResponse generatePrimes(@PathVariable int limit) {
    return new PrimesResponse(limit, generator.generatePrimes(limit));
  }

}