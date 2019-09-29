package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class ENDPOINT extends Node {
    String endpoint = "";

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("\\{");
        tokenizer.getAndCheckNext("'");
        endpoint = tokenizer.getNext();
        tokenizer.getAndCheckNext("'");
        tokenizer.getAndCheckNext("\\}");
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
