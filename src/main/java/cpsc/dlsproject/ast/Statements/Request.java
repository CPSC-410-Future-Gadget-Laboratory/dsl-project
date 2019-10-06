package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.ASTHelpers;
import cpsc.dlsproject.ast.BaseAST;

public class Request extends BaseAST {
    String requestType;

    public Request(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public void parse() {

        System.out.println(requestType);
        tokenizer.checkOpenBracket(tokenizer.getNext());
        while (!tokenizer.checkBracket(tokenizer.getCurrent())) {
            BaseAST currNode = null;
            if (tokenizer.checkToken("ENDPOINT")) {
                currNode = new Endpoint();
            } else if (tokenizer.checkToken("VAR")) {
                currNode = new Var();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new Conditional();
            } else if (ASTHelpers.CheckForIO()) {
                currNode = new IoStatement(tokenizer.getNext());
            } else if(tokenizer.checkToken("SEND")){
                currNode = new Send();
            }
            if (currNode == null) {
                System.out.println("Error, invalid token");
                System.exit(0);
            } else {
                tokenizer.getNext(); // Pop the keyword
                currNode.parse();
                children.add(currNode);
            }
        }
        tokenizer.getNext(); // Pop the last bracket
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
