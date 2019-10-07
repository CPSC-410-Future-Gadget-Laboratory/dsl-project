package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.BaseAST;

public class Send extends BaseAST {

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
}
