# infix-notation-calculator
In order to solve the given mathematical expression in Infix notation we first have to transform the expression into Reverse Polish Notation
###Infix Expression
Infix expressions are those expressions in which the operator is written in-between the two or more operands. Usually, we use infix expression. For example, consider the following expression.

a+b  
a/2+c*d-e*(f*g)  
a*(b+c)/d  

###Reverse Polish Notation (RPN)

Reverse Polish Notation provides the quickest way to enter data in a calculator because it eliminates the need for parenthesis. It was made mainstream by HP when they implemented it in their famous programmable calculators. It is also very simple to code into a computer program.
Examples below of the previous infix expressions:

ab+  
abc/de+*+f-  
ab+cd-*

###Infix notation to Postfix Notation Algorithm:

Initialize result as a blank string, Iterate through given expression, one character at a time


1. If the character is an operand, add it to the result.
2. If the character is an operator.
   - If the operator stack is empty then push it to the operator stack.
   - Else If the operator stack is not empty,
       - If the operator’s precedence is greater than or equal to the precedence of the stack top of the operator stack, then push the character to the operator stack.
       - If the operator’s precedence is less than the precedence of the stack top of operator stack then “pop out an operator from the stack and add it to the result until the stack is empty or operator’s precedence is greater than or equal to the precedence of the stack top of operator stack“. then push the operator to stack.

3. If the character is “(“, then push it onto the operator stack.
4. If the character is “)”, then “pop out an operator from the stack and add it to the result until the corresponding “(“ is encountered in operator stack. Now just pop out the “(“.

Once the expression iteration is completed and the operator stack is not empty, “pop out an operator from the stack and add it to the result” until the operator stack is empty.  The result will be our answer, postfix expression.

###Algorithm to evaluate the postfix expression

1. Scan the expression from left to right.
2. If we encounter any operand in the expression, then we push the operand in the stack.
3. When we encounter any operator in the expression, then we pop the corresponding operands from the stack.
4. When we finish with the scanning of the expression, the final value remains in the stack.
