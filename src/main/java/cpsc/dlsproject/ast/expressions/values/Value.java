package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.types.Type;

public abstract class Value extends Expression {
    public Type type;

    public abstract String toString();
}
