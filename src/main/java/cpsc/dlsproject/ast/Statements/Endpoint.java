package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.BaseAST;

public class Endpoint extends BaseAST {
    String endpoint = "";

    @Override
    public void parse() {
        endpoint = tokenizer.getNext();
        tokenizer.getNext().equals(";");
        System.out.println(endpoint);
    }
}
