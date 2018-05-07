package com.mateuszpomorski.primenumbersapi.controllers;

import com.mateuszpomorski.primenumbersapi.model.PrimesResponse;
import com.mateuszpomorski.primenumbersapi.service.PrimeNumbersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primes")
public class PrimesController {

  private final PrimeNumbersService primeNumbersService;

  public PrimesController(PrimeNumbersService primeNumbersService) {
    this.primeNumbersService = primeNumbersService;
  }

  @GetMapping("/{limit}")
  public PrimesResponse generatePrimes(@PathVariable int limit) {
    return new PrimesResponse(limit, primeNumbersService.generatePrimeNumbers(limit));
  }

}