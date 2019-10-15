package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class NumberValue extends Value {
    /**
     * Type number.
     */
    public Type type = Type.NUMBER;
    /**
     * Type value.
     */
    public final double value;

    public NumberValue(double d) {
        value = d;
    }
}
