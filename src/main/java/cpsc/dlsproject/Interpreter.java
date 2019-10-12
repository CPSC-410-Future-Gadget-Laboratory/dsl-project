package cpsc.dlsproject;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.visitors.ServerBuilderVisitor;

public class Interpreter {
  private final String script;

  private Interpreter(String script) {
    this.script = script;
  }

  public static Interpreter loadScriptFromString(String script) {
    if (script == null) {
      throw new NullPointerException("Script should not be null");
    }
    return new Interpreter(script);
  }

  public void runProgram() {
    Tokenizer.makeTokenizer(script);
    Program program = new Program();
    program.parse();

    ServerBuilderVisitor serverBuilder = new ServerBuilderVisitor(program);
    serverBuilder.run();
  }

  public static void main(String[] args) {
    Interpreter.loadScriptFromString(
            "GET \"/path/to/success\" {\n"
                + "  SEND {\n"
                + "    200;\n"
                + "    \"here is to your success!\";\n"
                + "  }\n"
                + "}")
        .runProgram();
  }
}
