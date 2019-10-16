package cpsc.dlsproject.utils;

import com.sun.net.httpserver.HttpExchange;
import cpsc.dlsproject.ast.expressions.values.Value;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, Value> variables = new HashMap<String, Value>();
    private HttpExchange httpExchange;

    public void setValue(String identifier, Value value) {
        variables.put(identifier, value);
    }

    public Value getValue(String identifier) {
        return variables.get(identifier);
    }

    public void setHttpExchange(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public void clearHttpExchange() {
        this.httpExchange = null;
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }

    public void clear() {
        variables.clear();
    }
}
