package cpsc.dlsproject.ast.expressions.values;


/**
 * A wrapper class representing the "boolean".
 * e.g. true or false.
 */
public class BooleanValue extends Value {
    /**
     * the value of the Boolean.
     */
    public boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }
}
