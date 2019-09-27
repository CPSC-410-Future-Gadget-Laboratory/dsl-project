package cpsc.dlsproject.ast;

public abstract class BaseAST {

    /**
     * Performs type checking on AST and raises
     * error when inconsistent types are caught.
     */
    public abstract void typeCheck();
}