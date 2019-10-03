package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class ENDPOINT extends Node {
    String endpoint = "";

    @Override
    public void parse() {
            tokenizer.getAndCheckNext("ENDPOINT");
            endpoint = tokenizer.getNext();
            tokenizer.checkCurrent().equals(";");
            System.out.println(endpoint);

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
