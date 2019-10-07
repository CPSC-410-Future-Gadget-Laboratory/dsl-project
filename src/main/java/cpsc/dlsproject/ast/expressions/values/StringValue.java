package cpsc.dlsproject.ast.expressions.values;

public class StringValue extends Value {

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
