package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.BaseAST;

public class IoStatement extends BaseAST {

    private boolean readfile;
    private String path;

    public IoStatement(String IO) {
        readfile = (IO == "READFILE");
    }

    public void parse() {
        path = tokenizer.getNext();
        tokenizer.getAndCheckNext("\\}");
    }
}
