package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class CONDITIONAL extends Node {
    private boolean IF;

    public CONDITIONAL(String condType) {
        IF = (condType == "IF");
    }

    private boolean CheckForLogical() {
        return tokenizer.checkToken("AND") || tokenizer.checkToken("OR") || tokenizer.checkToken("<") || tokenizer.checkToken(">") || tokenizer.checkToken("==") ||
                tokenizer.checkToken("!=") || tokenizer.checkToken("<=") || tokenizer.checkToken(">=");
    }

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("\\{");
        tokenizer.getAndCheckNext("{");
        if (!CheckForLogical()) {
            System.out.println("Not a logical token. Error");
            System.exit(0);
        }

        LOGICAL logicalNode = new LOGICAL(tokenizer.getNext());
        logicalNode.parse();
        children.add(logicalNode);

        while (!tokenizer.checkToken("\\}")) {
            Node currNode = null;
            if (tokenizer.checkToken("ENDPOINT")) {
                currNode = new ENDPOINT();
            } else if (tokenizer.checkToken("VAR")) {
                currNode = new VAR();
            } else if (Helpers.CheckForRequestType()) {
                currNode = new REQUEST(tokenizer.getNext());
            } else if (Helpers.CheckForIO()) {
                currNode = new IO(tokenizer.getNext());
            }
            if (currNode == null){
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
