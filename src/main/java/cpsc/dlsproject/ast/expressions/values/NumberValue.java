package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class NumberValue extends Value {
    public Type type = Type.NUMBER;

    public final double value;

    public NumberValue(double d) {
        value = d;
    }

    @Override
    public void parse() {

    }
}
