package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.BaseAST;

public class Endpoint extends BaseAST {
    String endpoint = "";

    public void parse() {
        tokenizer.getAndCheckNext("\"");
        endpoint = tokenizer.getNext();
        tokenizer.getAndCheckNext("\"");
        tokenizer.getNext().equals(";");
        System.out.println(endpoint);
    }
}