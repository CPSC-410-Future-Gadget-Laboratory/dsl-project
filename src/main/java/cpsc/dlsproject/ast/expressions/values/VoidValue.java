package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class VoidValue extends Value {
    public Type type = Type.UNDEFINED;

    @Override
    public String toString() {
        return "UNDEFINED";
    }
}
