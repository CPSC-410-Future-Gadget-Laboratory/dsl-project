package cpsc.dlsproject.server;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.visitors.PrintVisitor;
import cpsc.dlsproject.visitors.ServerBuilderVisitor;

import java.io.IOException;
import java.util.ArrayList;

public class DevTestingEnvironment {
    /** Testing out the Server Class */
    public static void main(String[] args) throws IOException {
        ArrayList<Statement> body = new ArrayList<Statement>();
        Response response = new Response(200, "Some successful Message!", new ArrayList<VarDeclaration>());
        body.add(response);
        URLDeclaration url = new URLDeclaration("/", new ArrayList<String>());
        EndpointDeclaration endpoint = new EndpointDeclaration(RequestMethod.GET, url, body);
        ArrayList<EndpointDeclaration> endpoints = new ArrayList<EndpointDeclaration>();
        endpoints.add(endpoint);
        Program program = new Program(endpoints);


        PrintVisitor printVisitor = new PrintVisitor(program);
        try {
            System.out.println("Running script:\n" + printVisitor.run() + "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ServerBuilderVisitor serverBuilderVisitor = new ServerBuilderVisitor(program);
        serverBuilderVisitor.run();
        System.out.println("Server is now up and running...");
    }
}
