package cpsc.dlsproject.visitors;

import com.sun.net.httpserver.HttpExchange;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.BinaryOperator;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.*;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.server.Server;
import cpsc.dlsproject.utils.SymbolTable;
import cpsc.dlsproject.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/** A class representing a visitor for building the server. */
public class ServerBuilderVisitor extends ASTVisitor<Value> {
  private Server server;
  private SymbolTable variables;
  private int port = 8000;

  public ServerBuilderVisitor(Program program) {
    super(program);
  }

  public void stopServer() {
    server.stopServer();
  }

  public void setPort(int port) {
    this.port = port;
  }

  @Override
  public Value run() {
    try {
      server = Server.newBuilder().setPort(port).build();
      variables = new SymbolTable();
      this.visit(program);
      server.setupStatsEndpoint();
      server.setupLoggingEndpoint();
      server.startServer();
      System.out.println("Server is serving in port " + port);
    } catch (IOException | ServerEvaluationError e) {
      throw new RuntimeException("Failed building the server, " + e);
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

    server.setHandler(
        endpoint.url.url,
        httpExchange -> {
          // Setup environment for execution.
          variables.setHttpExchange(httpExchange);
          server.increaseEndpointHitFrequency(endpoint.url.url);
          long id = server.incrementAndGetID();
          server.addRequestToServerLogs(httpExchange, id);
          for (Statement statement : endpoint.statements) {
            try {
              this.visit(statement);
            } catch (Exception e) {
              String errorMessage = "Sorry, there has been an error on the server side. :(";
              httpExchange
                  .getResponseBody()
                  .write(
                      errorMessage
                          .getBytes(StandardCharsets.UTF_8));
              System.out.println(
                  "500: There is an error when evaluating the statement. It is more likely to be the interpreter's error, not the developer's.");
              server.addResponseToServerLogs(httpExchange, 500, errorMessage, "text/html", id);
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
    ArrayList<Statement> toBeExecuted =
        conditionResult.value ? conditional.thenCase : conditional.elseCase;

    for (Statement statement : toBeExecuted) {
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
    String replacedMsg = null;
    try {
      replacedMsg = Utils.getUtilsObj().replaceEmbeddedValues(variables, response.message);
    } catch (Exception e) {
      throw new ServerEvaluationError("Error when replacing embedded values.");
    }
    try {
      byte body[] = replacedMsg.getBytes("UTF-8");
      httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
      httpExchange.sendResponseHeaders(response.statusCode, body.length);

      OutputStream out = httpExchange.getResponseBody();
      out.write(body);
      out.close();
      server.addResponseToServerLogs(httpExchange, response.statusCode, response.message, "text/html" /* ContentType */, server.getCurrentId());
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
      return new BooleanValue(
          ((NumberValue) leftResult).value <= ((NumberValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.EQUAL) {
      return new BooleanValue(
          ((NumberValue) leftResult).value == ((NumberValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.NOTEQUAL) {
      return new BooleanValue(
          ((NumberValue) leftResult).value != ((NumberValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.GEQUAL) {
      return new BooleanValue(
          ((NumberValue) leftResult).value >= ((NumberValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.GREATER) {
      return new BooleanValue(
          ((NumberValue) leftResult).value >= ((NumberValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.AND) {
      return new BooleanValue(
          ((BooleanValue) leftResult).value && ((BooleanValue) rightResult).value);
    } else if (binOp.operator == BinaryOperator.OR) {
      return new BooleanValue(
          ((BooleanValue) leftResult).value || ((BooleanValue) rightResult).value);
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
  }

  private static class ServerEvaluationError extends Exception {
    private String message;

    private ServerEvaluationError(String message) {
      this.message = message;
    }

    @Override
    public String getMessage() {
      return message;
    }
  }
}
