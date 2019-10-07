package cpsc.dlsproject.ast.expressions.values;

public class BooleanValue extends Value {
    public boolean isValue() {
        return value;
    }

    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public void parse() {

    }
}
