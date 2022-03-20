package com.aer.sample.infixnotationcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
  @Test
  void simpleSumTest() {
    var result = Calculator.calculate(("5+2"));
    assertEquals(7, result);
  }

  @Test
  void simpleSubTest() {
    var result = Calculator.calculate(("5-2"));
    assertEquals(3, result);
  }

  @Test
  void simpleMultiplyTest() {
    var result = Calculator.calculate(("5*2"));
    assertEquals(10, result);
  }

  @Test
  void simpleDivisionTest() {
    var result = Calculator.calculate(("5/2"));
    assertEquals(2.5, result);
  }

  @Test
  void complexExpressionTest() {
    var result = Calculator.calculate(("5+3*2+(5/2-3)"));
    assertEquals(10.5, result);
  }

  @Test
  void complexExpressionWithDigitNumberTest() {
    var result = Calculator.calculate(("52+3*21+(10/2-30)"));
    assertEquals(90, result);
  }
}

