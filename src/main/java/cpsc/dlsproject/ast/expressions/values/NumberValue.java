package cpsc.dlsproject.ast.expressions.values;

public class NumberValue extends Value {

    private double value;

    public double getValue() {
        return value;
    }

    public NumberValue(double d) {
        value = d;
    }

    @Override
    public void parse() {

    }
}
