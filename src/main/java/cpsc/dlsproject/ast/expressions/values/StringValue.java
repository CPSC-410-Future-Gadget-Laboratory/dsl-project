package cpsc.dlsproject.ast.expressions.values;

/**
 * A class representing String in our language.
 * e.g. "", "something", etc.
 */
public class StringValue extends Value {
    /**
     * the value of the string.
     */
    public StringValue value;

    public StringValue(StringValue value) {
        this.value = value;
    }
}
