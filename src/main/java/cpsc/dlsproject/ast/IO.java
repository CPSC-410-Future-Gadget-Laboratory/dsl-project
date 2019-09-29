package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class IO extends Node {

    private boolean readfile;
    private String path;

    public IO(String IO) {
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
