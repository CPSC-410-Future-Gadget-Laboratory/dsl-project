package cpsc.dlsproject.ast.Expressions.Values;

public class BooleanValue extends Value {
    public boolean isValue() {
        return value;
    }

    private boolean value;

    BooleanValue(boolean value) {
        this.value = value;
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
