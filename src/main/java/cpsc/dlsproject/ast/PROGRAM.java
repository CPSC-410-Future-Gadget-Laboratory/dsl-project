package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

import java.util.ArrayList;

public class PROGRAM extends Node {

    private ArrayList<Node> nodes = new ArrayList<>(); // Array list of instructions. Each node is the root node of an AST. Separate blocks of instructions are separate trees

    @Override
    public void parse() {
        int openBrackets = tokenizer.checkOpenBrackets();
        int closedBrackets = 0;
        while (!tokenizer.checkBracket(tokenizer.getCurrent())) {
            Node currNode = null;
            if (ASTHelpers.CheckForRequestType()) {
                currNode = new REQUEST(tokenizer.getNext());
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL(tokenizer.getNext());
            }
            if (currNode == null){
                System.out.println("Error, invalid token");
                break;
                // throw exception
//                System.exit(0);
            }
            if (tokenizer.checkBracket(tokenizer.checkCurrent())){
                closedBrackets++;
            }
            currNode.parse();
            nodes.add(currNode);
        }
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
