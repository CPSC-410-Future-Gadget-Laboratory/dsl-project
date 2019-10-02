package cpsc.dlsproject.ast.statements;

import java.util.List;

/**
 * A class representing Endpoint Declaration in a program.
 * e.g.
 * GET = {
 *     ENDPOINT = "v1/something/";
 *     SEND = {
 *         200;
 *         "This is a response.";
 *     }
 * }
 */
public class EndpointDeclaration extends Statement {
    /**
     * Request method type (one of GET, POST, PUT, DELETE).
     */
    public RequestMethod requestMethodType;

    /**
     * List of statements in the body.
     * A valid endpoint should have URLDeclaration as first statement,
     * and at least one RespondDeclaration.
     */
    public List<Statement> statements;
}
