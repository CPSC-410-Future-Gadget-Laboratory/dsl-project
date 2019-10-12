package cpsc.dlsproject.ast.expressions;

public class VarAccess extends Expression {
    public String identifier;

    public VarAccess(String s) {
        identifier = s;
    }

    @Override
    public void parse() {
       identifier = tokenizer.getNext();
    }
}
