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
            } else if (tokenizer.checkToken("VAR")) {
                currNode = new VAR();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL(tokenizer.getNext());
            } else if (ASTHelpers.CheckForIO()) {
                currNode = new IO(tokenizer.getNext());
            }
            if (currNode == null) {
                System.out.println("Error, invalid token");
                System.exit(0);
            }
            currNode.parse();
            children.add(currNode);
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
