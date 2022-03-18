package com.aer.sample.polishreversenotation;

public class Main {

  public static void main(String[] args) {
    if (!args[0].isEmpty()) {
      run(args[0]);
    }
  }

  private static void run(String arg) {
    String expression = String.valueOf(arg);
    Double result = Calculator.calculate(expression);
    System.out.println(expression + " = " + result.toString());
  }

}
