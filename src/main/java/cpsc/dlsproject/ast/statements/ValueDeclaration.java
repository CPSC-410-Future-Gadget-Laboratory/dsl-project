package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.types.Type;
import cpsc.dlsproject.ast.expressions.Expression;

/**
 * A class representing value representation (either
 * from explicit declaration or from implicit declaration
 * from url declaration).
 *
 * e.g. Number something = 2;
 */
public class ValueDeclaration extends Statement {
    /**
     * The identifier of the value.
     */
    public String name;

    /**
     * The type of the value.
     */
    public Type type;

    /**
     * The expression that evaluates to the type of the value.
     */
    public Expression expression;

    public ValueDeclaration() {

    }

    public ValueDeclaration(String name, Type type, Expression expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void parse() {

    }
}
