package com.aer.sample.polishreversenotation;

import java.util.*;

public class InfixToPrnConverter {

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
    Queue<String> result = new ArrayDeque<>();
    Stack<String> operands = new Stack();
    for (int i = 0; i < trimmedToken.length(); i++) {
      String symbol = getString(trimmedToken, i);
      if (isOperand(symbol)) {
        while (!operands.isEmpty() && precedence.get(operands.peek()) >= precedence.get(symbol)) {
          result.add(operands.pop());
        }
        operands.push(symbol);
      } else if (symbol.equals("(")) {
        operands.push(symbol);
      } else if (symbol.equals(")")) {
        String operand = operands.pop();
        while (!operand.equals("(")) {
          result.add(operand);
          operand = operands.pop();
        }
      } else {
        String number = symbol;
        while (i + 1 < trimmedToken.length() && checkNumber(symbol, getString(trimmedToken, i + 1))) {
          number += getString(trimmedToken, i+1);
          i++;
        }
        result.add(number);
      }

    }
    addRemainingOperands(result, operands);
    return result.toString().

      replaceAll("\\[|\\]|\\s", "");

  }

  private static String getString(String expression, int index) {
    return String.valueOf(expression.charAt(index));
  }

  private static boolean checkNumber(String operator1, String operator2) {
    try {
      String operator = operator1 + operator2;
      Double.parseDouble(operator);
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }

  private static boolean isOperand(String symbol) {
    List<String> operands = Arrays.asList("+", "-", "*", "/");
    return operands.contains(symbol);
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
