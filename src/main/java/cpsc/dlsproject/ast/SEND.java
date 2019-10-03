package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Node;

public class SEND extends Node {
    String code = "";
    String message = "";

    @Override
    public void parse() {
        tokenizer.checkOpenBracket(tokenizer.getNext());
        while(!tokenizer.checkBracket(tokenizer.getNext()) && !tokenizer.checkBracket(tokenizer.checkNext())){
            code = tokenizer.getNext();
            tokenizer.getNext().equals(";");
            message = tokenizer.getNext();
            tokenizer.getCurrent().equals(";");
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
