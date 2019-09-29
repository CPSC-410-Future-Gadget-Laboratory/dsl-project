package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class REQUEST extends Node {
    String requestType;

    public REQUEST(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public void parse() {
        System.out.println(requestType);
        tokenizer.getAndCheckNext("\\{");
        while (!tokenizer.checkToken("\\}")) {
            Node currNode = null;
            if (tokenizer.checkToken("ENDPOINT")) {
                currNode = new ENDPOINT();
            }
            currNode.parse();
        }
        tokenizer.getNext(); // Pop the end token
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
