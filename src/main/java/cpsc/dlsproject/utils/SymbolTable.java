package cpsc.dlsproject.utils;

import cpsc.dlsproject.ast.expressions.values.Value;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, Value> variables;

    public void setValue(String identifier, Value value) {
        variables.put(identifier, value);
    }

    public Value getValue(String identifier) {
        return variables.get(identifier);
    }

    public void clear() {
        variables.clear();
    }
}
