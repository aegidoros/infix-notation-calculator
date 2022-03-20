package com.aer.sample.infixnotationcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfixToPrnConverterTest {
  @Test
  void simpleSumExpressionTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("5+2");
    assertEquals("5,2,+", result);
  }

  @Test
  void simpleSubExpressionTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("5-2");
    assertEquals("5,2,-", result);
  }

  @Test
  void simpleMultiplyExpressionTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("5*2");
    assertEquals("5,2,*", result);
  }

  @Test
  void simpleDivisionExpressionTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("5/2");
    assertEquals("5,2,/", result);
  }

  @Test
  void complexExpressionTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("5+3*2+(5/2-3)");
    assertEquals("5,3,2,*,+,5,2,/,3,-,+", result);
  }

  @Test
  void complexExpressionWithDigitNumberTest() {
    String result = InfixToPrnConverter.convertInfixToPRN("52+3*21+(10/2-30)");
    assertEquals("52,3,21,*,+,10,2,/,30,-,+", result);
  }
}
