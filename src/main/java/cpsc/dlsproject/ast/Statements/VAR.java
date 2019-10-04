package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.Expressions.Expression;
import cpsc.dlsproject.tools.Variable;
import cpsc.dlsproject.tools.VariableMap;

public class VAR extends BaseAST {
    public String name;
    public Expression expression;

    @Override
    public void parse() {
//        tokenizer.getAndCheckNext("\\{");
        name = tokenizer.getNext();
        String type = tokenizer.getNext();
        String value = tokenizer.getNext();
        VariableMap.getVariableMap().put(name, new Variable(type, value));
        tokenizer.getAndCheckNext("\\}");
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
