package cpsc.dlsproject.ast.Expressions;

public class BinaryOperation extends Expression{
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
            case "-" :
                operator = BinaryOperator.MINUS;
                break;
            case "MULTI" :
                operator = BinaryOperator.MULTIPLY;
                break;
            case "/" :
                operator = BinaryOperator.DIVISION;
                break;
            case ">" :
                operator = BinaryOperator.GREATERTHAN;
                break;
            case "<" :
                operator = BinaryOperator.LESSTHAN;
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

    @Override
    public void parse() {

    }

    @Override
    public void evaluate() {

    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }
}
