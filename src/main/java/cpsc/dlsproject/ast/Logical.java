package cpsc.dlsproject.ast;

import cpsc.dlsproject.ast.statements.Var;

public class Logical extends BaseAST {

    private String oper;

    public Logical(String oper) {
        this.oper = oper;
    }

    public void parse() {
        Var cond1 = new Var();
        cond1.parse();
        Var cond2 = new Var();
        cond2.parse();
    }
}
