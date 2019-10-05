package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.BaseAST;

public class SEND extends BaseAST {

    private int statusCode;
    String message = "";

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void parse() {
        tokenizer.checkOpenBracket(tokenizer.getNext());
        while(!tokenizer.checkNext().equals("}")){
            this.statusCode = Integer.parseInt(tokenizer.getNext());
            tokenizer.getNext().equals(";");
            tokenizer.getNext().equals("\"");
            message = tokenizer.getNext();
            tokenizer.getNext().equals("\"");
            tokenizer.getNext().equals(";");
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
