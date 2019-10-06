package cpsc.dlsproject.ast;

import cpsc.dlsproject.ast.Statements.Var;

public class Logical extends BaseAST {

    private String oper;

    public Logical(String oper) {
        this.oper = oper;
    }

    @Override
    public void parse() {
        Var cond1 = new Var();
        cond1.parse();
        Var cond2 = new Var();
        cond2.parse();
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
