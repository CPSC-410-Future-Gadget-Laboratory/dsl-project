package cpsc.dlsproject.ast.expressions.values;

/**
 * A class representing a number in our language.
 * e.g. 1, 2, 3, 4, -1, 0, 0.5, etc.
 */
public class NumberValue extends Value {
    /**
     * the value of the Number.
     */
    private double value;

    public NumberValue(double value) {
        this.value = value;
    }
}
