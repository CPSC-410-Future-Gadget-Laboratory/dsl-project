package cpsc.dlsproject.ast;

import cpsc.dlsproject.ast.Statements.CONDITIONAL;
import cpsc.dlsproject.ast.Statements.REQUEST;

import java.util.ArrayList;

public class PROGRAM extends BaseAST {

    private ArrayList<BaseAST> nodes = new ArrayList<>(); // Array list of instructions. Each node is the root node of an AST. Separate blocks of instructions are separate trees

    @Override
    public void parse() {
        tokenizer.getNext(); // Pop START keyword
        while (tokenizer.moreTokens()) {
            BaseAST currNode = null;
            if (ASTHelpers.CheckForRequestType()) {
                currNode = new REQUEST(tokenizer.getNext());
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL();
            }
            if (currNode == null){
                System.out.println("Error, invalid token");
                System.exit(0);
            }
            currNode.parse();
            nodes.add(currNode);
        }
        System.out.println("Done with PROGRAM!");
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
