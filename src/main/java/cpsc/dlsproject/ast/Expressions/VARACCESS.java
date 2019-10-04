package cpsc.dlsproject.ast.Expressions;

public class VARACCESS extends Expression {
    public String identifier;

    public VARACCESS(String s) {
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
