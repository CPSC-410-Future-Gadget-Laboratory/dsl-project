package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.types.Type;
import cpsc.dlsproject.ast.expressions.Expression;

/**
 * A class representing value representation (either
 * from explicit declaration or from implicit declaration
 * from url declaration).
 *
 */
public class ValueDeclaration extends Statement {
    public String identifier;
    public Type type;
    public Expression expression;
}
