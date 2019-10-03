package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;
import cpsc.dlsproject.tools.Variable;
import cpsc.dlsproject.tools.VariableMap;

public class VAR extends Node {
    private String name;

    @Override
    public void parse() {
//        tokenizer.getAndCheckNext("\\{");
        name = tokenizer.getNext();
        if (!VariableMap.getVariableMap().containsKey(name)) {
            String type = tokenizer.getNext();
            String value = tokenizer.getNext();
            VariableMap.getVariableMap().put(name, new Variable(type, value));
            tokenizer.getAndCheckNext("\\}");
        }
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
