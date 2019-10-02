package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.statements.Statement;

import java.util.List;

/**
 * A class representing URL string of an endpoint.
 * Also keeps a Set of implicit Val declarations.
 *
 * e.g. ENDPOINT = "v1/timesheets/";
 * (Note: ENDPOINT is a reserved word in this language).
 */
public class URLDeclaration extends Statement {
    /**
     * The string of the url.
     */
    public String url;

    /**
     * The list of named placeholders parsed from the
     * url that will be replaced with real values.
     */
    public List<String> params;
}
