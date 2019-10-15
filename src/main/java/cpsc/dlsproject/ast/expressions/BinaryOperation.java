package cpsc.dlsproject.ast.expressions;

public class BinaryOperation extends Expression {
    /**
     * The operator of the binary operation.
     */
    public BinaryOperator operator;

    /**
     * The left hand side operand.
     */
    public Expression lhs;

    /**
     * The right hand side of the operand.
     */
    public Expression rhs;

    public BinaryOperation(String oper) {
        switch (oper) {
            case "PLUS" :
                operator = BinaryOperator.PLUS;
                break;
            case "MINUS" :
                operator = BinaryOperator.MINUS;
                break;
            case "MULTI" :
                operator = BinaryOperator.MULTIPLY;
                break;
            case "DIV" :
                operator = BinaryOperator.DIVISION;
                break;
            case ">" :
                operator = BinaryOperator.GREATER;
                break;
            case "<" :
                operator = BinaryOperator.LESSER;
                break;
            case ">=" :
                operator = BinaryOperator.GEQUAL;
                break;
            case "<=" :
                operator = BinaryOperator.LEQUAL;
                break;
            case "==" :
                operator = BinaryOperator.EQUAL;
                break;
            case "!=" :
                operator = BinaryOperator.NOTEQUAL;
                break;
            default:
                System.out.println("Reached default case in BinaryOperation. Should be impossible.");
                System.exit(0);
        }
    }

    public BinaryOperation() {}
}
