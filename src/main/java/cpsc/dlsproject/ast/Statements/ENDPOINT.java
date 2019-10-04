package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.BaseAST;

public class ENDPOINT extends BaseAST {
    String endpoint = "";

    @Override
    public void parse() {
            tokenizer.getAndCheckNext("ENDPOINT");
            endpoint = tokenizer.getNext();
            tokenizer.checkCurrent().equals(";");
            System.out.println(endpoint);

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
