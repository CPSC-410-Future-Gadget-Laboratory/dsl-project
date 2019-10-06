package cpsc.dlsproject.ast.Expressions.Values;

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
