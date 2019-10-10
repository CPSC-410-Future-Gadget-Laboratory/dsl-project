package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.*;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.server.Server;
import cpsc.dlsproject.types.Type;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * A class representing a visitor for building the server.
 */
public class ServerBuilderVisitor extends ASTVisitor<Value> {
    public ArrayList<String> errorMessages;
    public Server server;
    public SymbolTable variables;

    public ServerBuilderVisitor(Program program) {
        super(program);
    }

    @Override
    public Value run() {
        try {
            server = Server.newBuilder().setPort(8000).build();
            this.visit(program);
        } catch (IOException | ServerEvaluationError e) {
            System.out.println("Failed building the server.");
        }
        variables = new SymbolTable();
        server.startServer();
        return new VoidValue();
    }

    public void logError(BaseAST ast, String message) {
        errorMessages.add("Evaluation Error at " + ast.getClass().getName() + ": " + message);
        return;
    }

    public void logError(BaseAST ast) {
        logError(ast, "Failed evalating " + ast.getClass().getName());
    }

    @Override
    Value visit(Program program) throws ServerEvaluationError {
        for (EndpointDeclaration endpointDeclaration : program.endpoints) {
            this.visit(endpointDeclaration);
        }

        return new VoidValue();
    }

    @Override
    Value visit(EndpointDeclaration endpoint) throws ServerEvaluationError {
        // TODO: Talk to @Eishan where to specify HTTP Method.
        this.visit(endpoint.url);

        server.setHandler(endpoint.url.url, httpExchange -> {
            for (Statement statement: endpoint.statements) {
                statement.httpExchange = httpExchange;
                try {
                    this.visit(statement);
                } catch (Exception e) {
                    System.out.println("500: There is an error when evaluating the statement. It is more likely to be the interpreter's error, not the developer's.");
                    httpExchange.getResponseBody().write("IT WORKS!".getBytes(Charset.forName("UTF-8")));
                }
            }
        });

        return new VoidValue();
    }

    /**
     * @param conditional
     * @return true on successful evaluation, false otherwise.
     */
    @Override
    Value visit(Conditional conditional) throws Exception {
        // Assumption: always return Boolean Value.
        BooleanValue conditionResult = (BooleanValue) this.visit(conditional.condition);
        ArrayList<Statement> toBeExecuted = conditionResult.value ? conditional.thenCase : conditional.elseCase;

        for (Statement statement: toBeExecuted) {
            this.visit(statement);
        }

        return new VoidValue();
    }

    @Override
    Value visit(RequestMethod requestMethod) throws ServerEvaluationError {
        return null;
    }

    @Override
    Value visit(Response response) throws ServerEvaluationError {
        try {
            byte body[] = response.message.getBytes("UTF-8");

            response.httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            response.httpExchange.sendResponseHeaders(response.statusCode, body.length);

            OutputStream out = response.httpExchange.getResponseBody();
            out.write(body);
            out.close();
        } catch (IOException e) {
            System.out.println("There is an error when writing response into body.");
        }
        return new VoidValue();
    }

    @Override
    Value visit(URLDeclaration url) throws ServerEvaluationError {
        server.createEndpoint(url.url);
        return new VoidValue();
    }

    @Override
    Value visit(ValueDeclaration valueDeclaration) throws Exception {
        Value result = this.visit(valueDeclaration.expression);
        this.variables.setValue(valueDeclaration.name, result);
        return new VoidValue();
    }

    @Override
    Value visit(BinaryOperation binOp) throws ServerEvaluationError {
        return null;
    }

    @Override
    Value visit(BooleanValue bool) throws ServerEvaluationError {
        return null;
    }

    @Override
    Value visit(NumberValue num) throws ServerEvaluationError {
        return null;
    }

    @Override
    Value visit(StringValue str) throws ServerEvaluationError {
        return null;
    }

    private class ServerEvaluationError extends Exception {
        private String message;

        public ServerEvaluationError(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
