package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.expressions.Expression;

import java.util.List;

/**
 * A class representing a conditional in our language.
 */
public class Conditional extends Statement {

    /**
     * An expression that determines what block to execute.
     * MUST evaluate to a boolean.
     */
    public Expression condition;

    /**
     * The statements to execute if condition evaluates to TRUE,
     */
    public List<Statement> thenStatements;

    /**
     * The statements to execute if condition evaluates to FALSE,
     */
    public List<Statement> elseStatements;

    public Conditional(Expression condition, List<Statement> thenStatements, List<Statement> elseStatements) {
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }
}
