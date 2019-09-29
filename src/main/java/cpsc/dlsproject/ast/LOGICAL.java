package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class LOGICAL extends Node {

    private String oper;

    public LOGICAL(String oper) {
        this.oper = oper;
    }

    @Override
    public void parse() {
        VAR cond1 = new VAR();
        cond1.parse();
        VAR cond2 = new VAR();
        cond2.parse();
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
