package cpsc.dlsproject.ast;

import cpsc.dlsproject.ast.statements.Conditional;
import cpsc.dlsproject.ast.statements.EndpointDeclaration;
import cpsc.dlsproject.ast.statements.Request;

import java.util.ArrayList;

public class Program extends BaseAST {

    public ArrayList<EndpointDeclaration> endpoints;
    private ArrayList<BaseAST> nodes = new ArrayList<>(); // Array list of instructions. Each node is the root node of an AST. Separate blocks of instructions are separate trees

    public Program() {
    }

    public Program(ArrayList<EndpointDeclaration> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            BaseAST currNode = null;
            if (ASTHelpers.CheckForRequestType()) {
                currNode = new Request(tokenizer.getNext());
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new Conditional();
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
}
