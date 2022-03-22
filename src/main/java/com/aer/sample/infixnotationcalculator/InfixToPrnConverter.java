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
    return processToken(token);
  }

  private static String processToken(String token) {
    String trimmedToken = removeWhiteSpace(token);
    String regex = "\\(";
    String[] tokens = trimmedToken.split("\\(");
    Queue<String> result = new ArrayDeque<>();
    Stack<String> globalOperators = new Stack<>();
    Stack<String> iterationOperators = new Stack<>();
    for (String tokenGroup : tokens) {
      var cleanupToken = tokenGroup.replaceAll("\\)", "");
      processSymbols(new ArrayDeque<>(Arrays.asList(cleanupToken.split(""))), iterationOperators, result);
      globalOperators.addAll(iterationOperators);
      iterationOperators.clear();
    }
    addRemainingOperators(result, globalOperators);
    return result.toString().replaceAll("[\\[\\]\\s]", "");
  }

  private static void processSymbols(Queue<String> symbols, Stack<String> operators, Queue<String> result) {

    while (!symbols.isEmpty()) {
      String symbol = symbols.remove();
      switch (symbol) {
        case "+":
        case "-":
        case "/":
        case "*":
          processOperand(symbol, operators, result);
          break;
        default:
          processOperator(symbol, symbols, result);
      }
    }
  }

  private static void processOperand(String operator, Stack<String> operators, Queue<String> result) {
    while (!operators.isEmpty() && precedence.get(operators.peek()) >= precedence.get(operator)) {
      result.add(operators.pop());
    }
    operators.push(operator);
  }

  private static void processOperator(String operator, Queue<String> symbols, Queue<String> result) {
    StringBuilder multipleDigitOperator = new StringBuilder();
    multipleDigitOperator.append(operator);
    while (isNextNumber(symbols)) {
      multipleDigitOperator.append(symbols.remove());
    }
    result.add(operator);
  }

  private static boolean isNextNumber(Queue<String> symbols) {
    return (!symbols.isEmpty() && isNumber(symbols.peek()));
  }

  private static boolean isNumber(String symbol) {
    try {
      Integer.valueOf(symbol);
      return true;
    } catch (NumberFormatException exception) {
      System.out.println("The symbol is not a number");
      return false;
    }
  }

  private static String removeWhiteSpace(String token) {
    return token.replaceAll("\\s", "");
  }

  private static void addRemainingOperators(Queue<String> result, Stack<String> operands) {
    while (!operands.isEmpty()) {
      result.add(operands.pop());
    }
  }

}
