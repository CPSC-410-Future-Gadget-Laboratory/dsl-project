package cpsc.dlsproject.visitors;

import com.sun.net.httpserver.HttpExchange;
import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.BinaryOperator;
import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.*;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.server.Server;
import cpsc.dlsproject.utils.SymbolTable;

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

    public void stopServer() {
        server.stopServer();
    }

    @Override
    public Value run() {
        try {
            server = Server.newBuilder().setPort(8000).build();
            variables = new SymbolTable();
            this.visit(program);
            server.startServer();
            System.out.println("Server is serving in port 8000...");
            return new VoidValue();
        } catch (IOException | ServerEvaluationError e) {
            System.out.println("Failed building the server.");
        }

        return new VoidValue();
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
        this.visit(endpoint.url);

        server.setHandler(endpoint.url.url, httpExchange -> {
            // Setup environment for execution.
            variables.setHttpExchange(httpExchange);

            for (Statement statement: endpoint.statements) {
                try {
                    this.visit(statement);
                } catch (Exception e) {
                    httpExchange.getResponseBody().write("Sorry, there has been an error on the server side. :(".getBytes(Charset.forName("UTF-8")));
                    System.out.println("500: There is an error when evaluating the statement. It is more likely to be the interpreter's error, not the developer's.");
                }
            }

            // Tear down environment after execution.
            variables.clearHttpExchange();
        });

        return new VoidValue();
    }

    @Override
    Value visit(VarAccess varAccess) throws Exception {
        return this.variables.getValue(varAccess.identifier);
    }

    /**
     * @param conditional
     * @return true on successful evaluation, false otherwise.
     */
    @Override
    Value visit(Conditional conditional) throws ServerEvaluationError {
        // Assumption: always return Boolean Value.
        BooleanValue conditionResult = null;
        try {
            conditionResult = (BooleanValue) this.visit(conditional.condition);
        } catch (Exception e) {
            throw new Error("Error evaluating expression");
        }
        ArrayList<Statement> toBeExecuted = conditionResult.value ? conditional.thenCase : conditional.elseCase;

        for (Statement statement: toBeExecuted) {
            try {
                this.visit(statement);
            } catch (Exception e) {
                throw new ServerEvaluationError("Error evaluating statement");
            }
        }

        return new VoidValue();
    }

    @Override
    Value visit(Response response) throws ServerEvaluationError {
        HttpExchange httpExchange = variables.getHttpExchange();
        try {
            byte body[] = response.message.getBytes("UTF-8");
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            httpExchange.sendResponseHeaders(response.statusCode, body.length);

            OutputStream out = httpExchange.getResponseBody();
            out.write(body);
            out.close();
        } catch (IOException e) {
            throw new ServerEvaluationError("There is an error when writing response into body.");
        }
        return new VoidValue();
    }

    @Override
    Value visit(URLDeclaration url) throws ServerEvaluationError {
        server.createEndpoint(url.url);
        return new VoidValue();
    }

    @Override
    Value visit(VarDeclaration varDeclaration) throws ServerEvaluationError {
        Value result = null;
        try {
            result = this.visit(varDeclaration.expression);
        } catch (Exception e) {
            throw new ServerEvaluationError("Error evaluating expression");
        }
        this.variables.setValue(varDeclaration.name, result);
        return new VoidValue();
    }

    @Override
    Value visit(BinaryOperation binOp) throws ServerEvaluationError {
        Value leftResult = null;
        Value rightResult = null;

        try {
            leftResult = this.visit(binOp.lhs);
            rightResult = this.visit(binOp.rhs);
        } catch (Exception e) {
            throw new ServerEvaluationError("Error evaluating expression.");
        }

        if (binOp.operator == BinaryOperator.PLUS) {
            return new NumberValue(((NumberValue) leftResult).value + ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.MINUS) {
            return new NumberValue(((NumberValue) leftResult).value - ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.MULTIPLY) {
            return new NumberValue(((NumberValue) leftResult).value * ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.DIVISION) {
            return new NumberValue(((NumberValue) leftResult).value / ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.LESSER) {
            return new BooleanValue(((NumberValue) leftResult).value < ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.LEQUAL) {
            return new BooleanValue(((NumberValue) leftResult).value <= ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.EQUAL) {
            return new BooleanValue(((NumberValue) leftResult).value == ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.NOTEQUAL) {
            return new BooleanValue(((NumberValue) leftResult).value != ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.GEQUAL) {
            return new BooleanValue(((NumberValue) leftResult).value >= ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.GREATER) {
            return new BooleanValue(((NumberValue) leftResult).value >= ((NumberValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.AND) {
            return new BooleanValue(((BooleanValue) leftResult).value && ((BooleanValue) rightResult).value);
        } else if (binOp.operator == BinaryOperator.OR) {
            return new BooleanValue(((BooleanValue) leftResult).value || ((BooleanValue) rightResult).value);
        } else {
            throw new ServerEvaluationError("Binary Operation is not specified/invalid.");
        }
    }

    @Override
    Value visit(NumberValue numVal) throws ServerEvaluationError {
        return numVal;
    }

    @Override
    Value visit(BooleanValue boolVal) throws ServerEvaluationError {
        return boolVal;
    }

    @Override
    Value visit(StringValue strValue) throws ServerEvaluationError {
        return strValue;
    };

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
