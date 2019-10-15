package cpsc.dlsproject.tools;
// Java program to convert
// infix to prefix.
import java.util.*;
public class InfixToPrefixConverter {

    // Function to check if
    // given character is
    // an operator or not.
    static boolean isOperator(String input) {
        char c = input.charAt(0);

        return (!(c >= 'a' && c <= 'z') &&
                !(c >= '0' && c <= '9') &&
                !(c >= 'A' && c <= 'Z'));
    }

    // Function to find priority
    // of given operator.
    static int getPriority(String input) {
        char C = input.charAt(0);

        if (C == '-' || C == '+')
            return 1;
        else if (C == '*' || C == '/')
            return 2;
        else if (C == '^')
            return 3;
        return 0;
    }

    // Function that converts infix
    // expression to prefix expression.
    public static String infixToPrefix(List<String> infix) {
        // stack for operators.
        Stack<String> operators = new Stack<String>();

        // stack for operands.
        Stack<String> operands = new Stack<String>();

        for (int i = 0; i < infix.size(); i++) {

            // If current character is an
            // opening bracket, then
            // push into the operators stack.
            if (infix.get(i).equals('(')) {
                operators.push(infix.get(i));
            }

            // If current character is a
            // closing bracket, then pop from
            // both stacks and push result
            // in operands stack until
            // matching opening bracket is
            // not found.
            else if (infix.get(i).equals(')')) {
                while (!operators.empty() &&
                        operators.peek().equals('(')) {

                    // operand 1
                    String op1 = operands.peek();
                    operands.pop();

                    // operand 2
                    String op2 = operands.peek();
                    operands.pop();

                    // operator
                    String op = operators.peek();
                    operators.pop();

                    // Add operands and operator
                    // in form operator +
                    // operand1 + operand2.
                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }

                // Pop opening bracket
                // from stack.
                operators.pop();
            }

            // If current character is an
            // operand then push it into
            // operands stack.
            else if (!isOperator(infix.get(i)))
            {
                operands.push(infix.get(i) + "");
            }

            // If current character is an
            // operator, then push it into
            // operators stack after popping
            // high priority operators from
            // operators stack and pushing
            // result in operands stack.
            else
            {
                while (!operators.empty() &&
                        getPriority(infix.get(i)) <=
                                getPriority(operators.peek()))
                {

                    String op1 = operands.peek();
                    operands.pop();

                    String op2 = operands.peek();
                    operands.pop();

                    String op = operators.peek();
                    operators.pop();

                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }

                operators.push(infix.get(i));
            }
        }

        // Pop operators from operators
        // stack until it is empty and
        // operation in add result of
        // each pop operands stack.
        while (!operators.empty())
        {
            String op1 = operands.peek();
            operands.pop();

            String op2 = operands.peek();
            operands.pop();

            String op = operators.peek();
            operators.pop();

            String tmp = op + op2 + op1;
            operands.push(tmp);
        }

        // Final prefix expression is
        // present in operands stack.

        String output = operands.peek();
        ArrayList<String> outputArray = new ArrayList<String>();
        int i = 0;

//
//        while(i < output.length()) {
//            if (output.charAt(0))
//        }

        return operands.peek();
    }

//    // Driver code
//    public static void main(String args[])
//    {
//        String s = "(A-B/C)*(A/K-L)";
//        System.out.println( infixToPrefix(s));
//    }
}