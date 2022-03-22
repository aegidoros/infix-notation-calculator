package com.aer.sample.infixnotationcalculator;

import java.util.*;
import java.util.function.Consumer;

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
    Queue<String> symbols = new ArrayDeque<>(Arrays.asList(trimmedToken.split("")));
    return convertSymbols(symbols);
  }

  private static String convertSymbols(Queue<String> symbols) {
    Queue<String> result = new ArrayDeque<>();
    Stack<String> operators = new Stack<>();
    processSymbols(symbols, operators, result);
    addRemainingOperators(result, operators);
    return result.toString().replaceAll("[\\[\\]\\s]", "");
  }

  private static void processSymbols(Queue<String> symbols, Stack<String> operands, Queue<String> result) {

    while (!symbols.isEmpty()) {
      String symbol = symbols.remove();
      switch (symbol) {
        case "+":
        case "-":
        case "/":
        case "*":
          processOperand(symbol, operands, result);
          operands.push(symbol);
          break;
        case "(":
          operands.push(symbol);
          break;
        case ")":
          processClosingParentheses(operands, result);
          break;
        default:
          var operator = processOperator(symbol, symbols);
          result.add(operator);
      }
    }
  }

  private static void processOperand(String operand, Stack<String> operands, Queue<String> result) {
    if (!operands.isEmpty() && precedence.get(operands.peek()) >= precedence.get(operand)) {
      result.add(operands.pop());
      processOperand(operand, operands, result);
    }
  }

  private static String processOperator(String operator, Queue<String> symbols) {
    StringBuilder multipleDigitOperator = new StringBuilder();
    processOperator(operator, symbols, multipleDigitOperator::append);
    return multipleDigitOperator.toString();
  }

  private static void processOperator(String operator, Queue<String> symbols, Consumer<String> addOperator) {
    addOperator.accept(operator);
    if (isNextNumber(symbols)) {
      processOperator(symbols.remove(), symbols, addOperator);
    }

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

  private static void processClosingParentheses(Stack<String> operands, Queue<String> result) {
    String operand = operands.pop();
    if (!operands.isEmpty() && !operand.equals("(")) {
      result.add(operand);
      processClosingParentheses(operands, result);
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
