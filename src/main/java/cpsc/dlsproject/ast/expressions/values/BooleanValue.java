package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class BooleanValue extends Value {
    public Type type = Type.BOOLEAN;
    public final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public void parse() {

    }
}
