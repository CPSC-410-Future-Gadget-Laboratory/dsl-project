package cpsc.dlsproject;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.visitors.ParseVisitor;
import cpsc.dlsproject.visitors.ServerBuilderVisitor;

public class Interpreter {
  private final String script;
  private ServerBuilderVisitor serverBuilder;
  private int port = 8000;

  private Interpreter(String script) {
    this.script = script;
  }

  public static Interpreter loadScriptFromString(String script) {
    if (script == null) {
      throw new NullPointerException("Script should not be null");
    }
    return new Interpreter(script);
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void runProgram() throws InterpreterException {
    Tokenizer.makeTokenizer(script);
    ParseVisitor parser = new ParseVisitor();
    Program program = null;
    try {
      program = (Program) parser.run();
    } catch (Exception e) {
      System.out.println("Throwing a parsing exception!");
      throw new InterpreterException(e);
    }

    serverBuilder = new ServerBuilderVisitor(program);
    serverBuilder.setPort(port);
    serverBuilder.run();
  }

  public void killProgram() {
    serverBuilder.stopServer();
  }

  public static void main(String[] args) {
    try {
      Interpreter.loadScriptFromString(
              "GET \"/\" {\n"
                  + "  VAR a: Number = 1;\n"
                  + "  VAR b: Number = 2;\n"
                  + "\n"
                  + "  VAR c: Boolean = true;\n"
                  + "  VAR d: Boolean = false;\n"
                  + "\n"
                  + "  VAR addition: Number = + a TO b;\n"
                  + "  VAR subtraction: Number = - a TO b;\n"
                  + "  VAR multiplication: Number = * a TO b;\n"
                  + "  VAR division: Number = \\ a TO b;\n"
                  + "  VAR andOp: Boolean = && c TO d;\n"
                  + "  VAR orOp: Boolean = || c TO d;\n"
                  + "  VAR lessThanOp: Boolean = < a TO b;\n"
                  + "  VAR equalOp: Boolean = == a TO b;\n"
                  + "  VAR notEqualOp: Boolean = != a TO b;\n"
                  + "  VAR greaterThanOp: Boolean = > a TO b;\n"
                  + "  VAR greaterThanOrEqualOp: Boolean = >= a TO b;\n"
                  + "\n"
                  + "  SEND {\n"
                  + "  200;\n"
                  + "  \"addition: {addition}, subtraction: {subtraction}, multiplication: {multiplication}, division: {division}, andOp: {andOp}, orOp: {orOp}, lessThanOp: {lessThanOp}, equalOp: {equalOp}, notEqualOp: {notEqualOp}, greaterThanOp: {greaterThanOp}\";\n"
                  + "  };\n"
                  + "}\n")
          .runProgram();
    } catch (InterpreterException e) {
      e.printStackTrace();
    }
  }

  private static class InterpreterException extends Exception {
    public InterpreterException(String msg) {
      super(msg);
    }

    public InterpreterException(Exception msg) {
      super(msg);
    }
  }
}
