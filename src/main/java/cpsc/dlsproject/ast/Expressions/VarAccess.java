package cpsc.dlsproject.ast.Expressions;

public class VarAccess extends Expression {
    public String identifier;

    public VarAccess(String s) {
        identifier = s;
    }

    @Override
    public void parse() {
       identifier = tokenizer.getNext();
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
