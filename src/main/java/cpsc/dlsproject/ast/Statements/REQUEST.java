package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.ASTHelpers;
import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.Statements.*;

public class REQUEST extends BaseAST {
    String requestType;

    public REQUEST(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public void parse() {

        System.out.println(requestType);
        tokenizer.checkOpenBracket(tokenizer.getNext());
        while (!tokenizer.checkBracket(tokenizer.getCurrent())) {
            BaseAST currNode = null;
            if (tokenizer.checkToken("ENDPOINT")) {
                currNode = new ENDPOINT();
            } else if (tokenizer.checkToken("VAR")) {
                currNode = new VAR();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL(tokenizer.getNext());
            } else if (ASTHelpers.CheckForIO()) {
                currNode = new IO(tokenizer.getNext());
            } else if(tokenizer.checkToken("SEND")){
                currNode = new SEND();
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
