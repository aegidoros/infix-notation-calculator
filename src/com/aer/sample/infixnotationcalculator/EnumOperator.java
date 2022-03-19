package com.aer.sample.infixnotationcalculator;

import java.util.Arrays;

public enum EnumOperator {

  ADD("+"),
  SUBTRACT("-"),
  MULTIPLY("*"),
  DIVIDE("/"),
  UNKNOWN("");

  private final String value;

  EnumOperator(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static EnumOperator parse(String value) {
    return Arrays.stream(EnumOperator.values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(UNKNOWN);
  }

}

