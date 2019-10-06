package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.BaseAST;

public class IoStatement extends BaseAST {

    private boolean readfile;
    private String path;

    public IoStatement(String IO) {
        readfile = (IO == "READFILE");
    }

    @Override
    public void parse() {
        path = tokenizer.getNext();
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
