package com.mateuszpomorski.primenumbersapi.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class ResourceReader {

  private ResourceReader() {
    // no need to instantiate
  }

  public static String readResourceAsString(String fileName) {
    try {
      return IOUtils.toString(
          ResourceReader.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"
      );
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read resource " + fileName);
    }
  }
}
