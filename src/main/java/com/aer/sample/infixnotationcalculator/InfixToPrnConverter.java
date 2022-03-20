package com.aer.sample.infixnotationcalculator;

import java.util.*;

public class InfixToPrnConverter {
  private InfixToPrnConverter() {
    throw new IllegalStateException("Utility class");
  }

  private static final Map<String, Integer> precedence;

  static {
    precedence = new HashMap<>();
    precedence.put("+", 1);
    precedence.put("-", 1);
    precedence.put("*", 2);
    precedence.put("/", 2);
    precedence.put("(", -1);

  }

  public static String convertInfixToPRN(String token) {

    String trimmedToken = removeWhiteSpace(token);
    Deque<String> result = new ArrayDeque<>();
    Stack<String> operands = new Stack<>();
    boolean isPrecedenceNumber = false;
    for (int i = 0; i < trimmedToken.length(); i++) {
      String symbol = getString(trimmedToken, i);
      switch (symbol) {
        case "+":
        case "-":
        case "/":
        case "*":
          while (!operands.isEmpty() && precedence.get(operands.peek()) >= precedence.get(symbol)) {
            result.add(operands.pop());
          }
          operands.push(symbol);
          isPrecedenceNumber=false;
          break;
        case "(":
          operands.push(symbol);
          isPrecedenceNumber=false;
          break;
        case ")":
          String operand = operands.pop();
          while (!operand.equals("(")) {
            result.add(operand);
            operand = operands.pop();
          }
          isPrecedenceNumber=false;
          break;
        default:
          if(isPrecedenceNumber){
            StringBuilder moreThanTwoDigitSymbol = new StringBuilder();
            moreThanTwoDigitSymbol.append(result.removeLast());
            moreThanTwoDigitSymbol.append(symbol);
            result.add(moreThanTwoDigitSymbol.toString());
          }else {
            result.add(symbol);
          }
          isPrecedenceNumber = true;
      }
    }
    addRemainingOperands(result, operands);
    return result.toString().replaceAll("[\\[\\]\\s]", "");
  }

  private static String getString(String expression, int index) {
    return String.valueOf(expression.charAt(index));
  }


  private static String removeWhiteSpace(String token) {
    return token.replaceAll("\\s", "");
  }

  private static void addRemainingOperands(Queue<String> result, Stack<String> operands) {
    while (!operands.isEmpty()) {
      result.add(operands.pop());
    }
  }

}
