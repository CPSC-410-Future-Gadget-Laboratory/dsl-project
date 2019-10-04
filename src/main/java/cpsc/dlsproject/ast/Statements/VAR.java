package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.tools.Variable;
import cpsc.dlsproject.tools.VariableMap;

public class VAR extends BaseAST {
    private String name;

    @Override
    public void parse() {
//        tokenizer.getAndCheckNext("\\{");
        String type = tokenizer.getNext();
        name = tokenizer.getNext();
        if (!VariableMap.getVariableMap().containsKey(name)) {
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
