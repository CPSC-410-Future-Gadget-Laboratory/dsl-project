package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class BooleanValue extends Value {
    public Type type = Type.BOOLEAN;

    public boolean isValue() {
        return value;
    }

    public boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public void parse() {

    }
}
