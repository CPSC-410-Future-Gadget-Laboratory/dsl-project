package cpsc.dlsproject.ast.statements;

import java.util.List;

/**
 * A class representing a Response Declaration.
 *
 * e.g.
 * SEND {
 *     400;
 *     "Something is not cool";
 * }
 */
public class Response extends Statement {
    /**
     * The status code to be returned.
     */
    public int statusCode;

    /**
     * Message returned to the client.
     */
    public String message;

    /**
     * A list of embedded values read from the message.
     */
    public List<VarDeclaration> embededValues;

    public Response(int statusCode, String message, List<VarDeclaration> embededValues) {
        this.statusCode = statusCode;
        this.message = message;
        this.embededValues = embededValues;
    }

    public Response() {

    }

    public void parse() {

    }
}
