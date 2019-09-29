package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PROGRAM extends Node {

    private ArrayList<Node> nodes = new ArrayList<>(); // Array list of instructions. Each node is the root node of an AST. Separate blocks of instructions are separate trees

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("START");
        while (!tokenizer.checkToken("END")) {
            Node currNode = null;
            if (tokenizer.checkToken("GET")) {
                currNode = new REQUEST(tokenizer.getNext());
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
