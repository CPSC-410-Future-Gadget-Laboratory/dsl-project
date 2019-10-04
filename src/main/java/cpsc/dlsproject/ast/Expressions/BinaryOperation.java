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
