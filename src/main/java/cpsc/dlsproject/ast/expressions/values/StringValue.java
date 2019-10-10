package cpsc.dlsproject.ast.expressions.values;

import cpsc.dlsproject.types.Type;

public class StringValue extends Value {
    public Type type = Type.STRING;

    private String value;

    public StringValue(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void parse() {

    }
}
