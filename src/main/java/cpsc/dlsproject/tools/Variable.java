package cpsc.dlsproject.tools;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Variable {
    static Variable create(String name, String value) {
        return new cpsc.dlsproject.tools.AutoValue_Variable(name, value);
    }
    abstract String name();
    abstract String value();
}
