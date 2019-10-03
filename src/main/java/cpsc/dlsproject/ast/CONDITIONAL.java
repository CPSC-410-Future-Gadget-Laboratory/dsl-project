package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;
import cpsc.dlsproject.tools.Variable;

import static cpsc.dlsproject.tools.VariableMap.variables;


public class CONDITIONAL extends Node {
    private boolean IF;
    private String var1 = "";
    private String var1Type = "";
    private String var2 = "";
    private String var2Type = "";
    private String operator = "";


    public CONDITIONAL(String condType) {
        IF = (condType == "IF");
    }

    private boolean CheckForLogical() {
        return tokenizer.checkToken("AND") || tokenizer.checkToken("OR") || tokenizer.checkToken("<") || tokenizer.checkToken(">") || tokenizer.checkToken("==") ||
                tokenizer.checkToken("!=") || tokenizer.checkToken("<=") || tokenizer.checkToken(">=");
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void parse() {
//        tokenizer.getAndCheckNext("\\{");
//        tokenizer.getAndCheckNext("{");
        var1 = tokenizer.getNext();
        if (!CheckForLogical()) {
            System.out.println("Not a logical token. Error");
            System.exit(0);
        }
        operator = tokenizer.getNext();
        var2 = tokenizer.getNext();

        if (isNumeric(var1)) {
            var1Type = "int";
            var2Type = "string";
        } else {
            var1Type = "string";
            var2Type = "int";
        }
        //put variable into map here

//        LOGICAL logicalNode = new LOGICAL(tokenizer.getNext());
//        logicalNode.parse();
//        children.add(logicalNode);

//        while (!tokenizer.checkToken("\\}")) {
//            Node currNode = null;
//            if (tokenizer.checkToken("ENDPOINT")) {
//                currNode = new ENDPOINT();
//            } else if (tokenizer.checkToken("VAR")) {
//                currNode = new VAR();
//            } else if (ASTHelpers.CheckForRequestType()) {
//                currNode = new REQUEST(tokenizer.getNext());
//            } else if (ASTHelpers.CheckForIO()) {
//                currNode = new IO(tokenizer.getNext());
//            } else if (tokenizer.checkToken("SEND")){
//                currNode = new SEND();
//            }
//            if (currNode == null){
//                System.out.println("Error, invalid token");
//                System.exit(0);
//            }
//            currNode.parse();
//            children.add(currNode);
//        }
//        tokenizer.getNext(); // Pop the end token

        while (!tokenizer.checkBracket(tokenizer.getCurrent())) {
            Node currNode = null;
            if (tokenizer.checkToken("ENDPOINT")) {
                currNode = new ENDPOINT();
            } else if (tokenizer.checkToken("VAR")) {
                currNode = new VAR();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL(tokenizer.getNext());
            } else if (ASTHelpers.CheckForIO()) {
                currNode = new IO(tokenizer.getNext());
            } else if (tokenizer.checkToken("SEND")) {
                currNode = new SEND();
            }
            if (currNode == null) {
                System.out.println("Error, invalid token");
//                System.exit(0);
            } else {
                currNode.parse();
                children.add(currNode);
            }
            tokenizer.getNext();
        }
        tokenizer.currentToken--;

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
