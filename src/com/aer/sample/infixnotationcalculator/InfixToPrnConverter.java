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
    Queue<String> result = new ArrayDeque<>();
    Stack<String> operands = new Stack<>();
    boolean isNumber = false;
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
          isNumber=false;
          break;
        case "(":
          operands.push(symbol);
          isNumber=false;
          break;
        case ")":
          String operand = operands.pop();
          while (!operand.equals("(")) {
            result.add(operand);
            operand = operands.pop();
          }
          isNumber=false;
          break;
        default:
          if(isNumber){
            symbol+=result.remove();
          }
          result.add(symbol);
          isNumber=true;
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
