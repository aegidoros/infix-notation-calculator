package com.aer.sample.polishreversenotation;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;

public class Calculator {

  public static Double calculate(String expression) {
    String prnExpression = InfixToPrnConverter.convertInfixToPRN(expression);
    Stack<Double> operands = new Stack<>();
    Arrays.stream(prnExpression.split(",")).forEach(symbol -> operateSymbol(operands, symbol));
    return operands.pop();
  }

  private static void operateSymbol(Stack<Double> operands, String symbol) {
    EnumOperator operator = EnumOperator.parse(symbol);
    switch (operator) {
      case ADD:
        applyOperationToOperands(operands, Double::sum);
        break;
      case SUBTRACT:
        applyOperationToOperands(operands, (operand1, operand2) -> operand2 - operand1);
        break;
      case MULTIPLY:
        applyOperationToOperands(operands, (operand1, operand2) -> operand2 * operand1);
        break;
      case DIVIDE:
        applyOperationToOperands(operands, (operand1, operand2) -> operand2 / operand1);
        break;
      default:
        operands.push(Double.parseDouble(symbol));
    }

  }

  private static void applyOperationToOperands(Stack<Double> operands, BiFunction<Double, Double, Double> operation) {
    operands.push(operation.apply(operands.pop(), operands.pop()));
  }

}
