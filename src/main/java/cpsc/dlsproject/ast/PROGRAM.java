package cpsc.dlsproject.ast;


import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.statements.EndpointDeclaration;

import java.util.List;

/**
 * A class representing the root of the whole program.
 * A program will consist of a list of declarations.
 */
public class Program extends BaseAST {

    /**
     * A list of endpoint declarations.
     */
    public List<EndpointDeclaration> endpoints;

    public Program(List<EndpointDeclaration> endpoints) {
        this.endpoints = endpoints;
    }
}
