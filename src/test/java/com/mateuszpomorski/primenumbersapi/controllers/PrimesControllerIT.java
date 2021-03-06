package com.mateuszpomorski.primenumbersapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mateuszpomorski.primenumbersapi.utils.ResourceReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimesControllerIT {

  private static final String PRIMES_PATH = "/primes";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnJsonPrimesResponse() throws Exception {
    //when
    mockMvc.perform(get(PRIMES_PATH + "/10"))
        //then
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "responses/primes_10.json"), true)
        );
  }

  @Test
  public void shouldReturnXmlPrimesResponse() throws Exception {
    //when
    mockMvc.perform(get(PRIMES_PATH + "/10")
        .accept(MediaType.APPLICATION_XML))
        //then
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
        .andExpect(content().xml(
            ResourceReader.readResourceAsString(
                "responses/primes_10.xml"))
        );
  }

  @Test
  public void shouldReturnABadRequestResponseForInvalidLimitParameter() throws Exception {
    //when
    mockMvc.perform(get(PRIMES_PATH + "/abc"))
        //then
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnABadRequestResponseForNegativeLimit() throws Exception {
    //when
    mockMvc.perform(get(PRIMES_PATH + "/-1"))
        //then
        .andExpect(status().isBadRequest());
  }
}