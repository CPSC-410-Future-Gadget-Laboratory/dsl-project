package cpsc.dlsproject;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.visitors.ParseVisitor;
import cpsc.dlsproject.visitors.ServerBuilderVisitor;

public class Interpreter {
  private final String script;
  private ServerBuilderVisitor serverBuilder;

  private Interpreter(String script) {
    this.script = script;
  }

  public static Interpreter loadScriptFromString(String script) {
    if (script == null) {
      throw new NullPointerException("Script should not be null");
    }
    return new Interpreter(script);
  }

  public void runProgram() throws InterpreterException {
    Tokenizer.makeTokenizer(script);
    ParseVisitor parser = new ParseVisitor();
      Program program = null;
      try {
          program = (Program) parser.run();
      } catch (Exception e) {
          throw new InterpreterException("Error while parsing.");
      }

      serverBuilder = new ServerBuilderVisitor(program);
    serverBuilder.run();
  }

  public void killProgram() {
    serverBuilder.stopServer();
  }

  public static void main(String[] args) {
      try {
          Interpreter.loadScriptFromString("GET \"/conditionalTrue\" {\n" +
          "    VAR a: Number = 1;\n" +
          "    VAR b: Number = 2;\n" +
          "    IF ( <  a TO b ) {\n" +
          "      SEND {\n" +
          "        200;\n" +
          "        \"something good\";\n" +
          "      };\n" +
          "    } ELSE {\n" +
          "      SEND {\n" +
          "        500;\n" +
          "        \"something bad\";\n" +
          "      };\n" +
          "    }\n" +
          "}\n" +
          "\n" +
          "GET \"/conditionalFalse\" {\n" +
          "    VAR a : Number = 1;\n" +
          "    VAR b : Number = 2;\n" +
          "    IF (> a TO b) {\n" +
          "      SEND {\n" +
          "        200;\n" +
          "        \"something good\";\n" +
          "      }\n" +
          "    } ELSE {\n" +
          "      SEND {\n" +
          "        500;\n" +
          "        \"something bad\";\n" +
          "      }\n" +
          "    }\n" +
          "}").runProgram();
      } catch (InterpreterException e) {
          e.printStackTrace();
      }
  }

    private class InterpreterException extends Throwable {
        public InterpreterException(String msg) {
            super(msg);
        }
    }
}
