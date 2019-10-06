package cpsc.dlsproject.ast.Expressions.Values;

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

    @Override
    public void evaluate() {

    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }
}
