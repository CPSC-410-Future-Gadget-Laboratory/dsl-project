package cpsc.dlsproject.ast.statements;

import com.sun.net.httpserver.HttpExchange;
import cpsc.dlsproject.ast.BaseAST;

public abstract class Statement extends BaseAST {
    /**
     * http exchange object associated to the statement.
     */
    public HttpExchange httpExchange;
}
