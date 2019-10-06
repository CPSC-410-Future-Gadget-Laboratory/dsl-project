package cpsc.dlsproject.ast.expressions;

/**
 * A class representing expressions involving binary operations.
 */
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

    public BinaryOperation(BinaryOperator operator, Expression lhs, Expression rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
