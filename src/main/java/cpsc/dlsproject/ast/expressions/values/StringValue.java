package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class StringValue extends Value {
    public Type type = Type.STRING;
    public final String value;

    public StringValue(String s) {
        value = s;
    }

}
