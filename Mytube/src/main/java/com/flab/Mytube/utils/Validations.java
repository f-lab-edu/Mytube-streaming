package com.flab.Mytube.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validations {

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
