package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class BooleanValue extends Value {
    /**
     * Type boolean.
     */
    public final Type type = Type.BOOLEAN;
    /**
     * Java's representation of DSL's boolean.
     */
    public final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }
}
