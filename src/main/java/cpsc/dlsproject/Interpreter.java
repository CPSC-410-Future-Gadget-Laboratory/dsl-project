package cpsc.dlsproject;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.visitors.ServerBuilderVisitor;

public class Interpreter {
    private String script;

    public void loadScriptFromString(String script) {
        this.script = script;
    }

    public void runProgram() throws Exception {
        if (script == null) {
            throw new Exception("Program script has not been loaded.");
        }
        Tokenizer.makeTokenizer(script);
        Program program = new Program();
        program.parse();

        ServerBuilderVisitor serverBuilder = new ServerBuilderVisitor(program);
        serverBuilder.run();
    }

    public static void main(String[] args) throws Exception {
        Interpreter interpreter = new Interpreter();
        interpreter.loadScriptFromString("GET \"/path/to/success\" {\n" +
                "  SEND {\n" +
                "    200;\n" +
                "    \"here is to your success!\";\n" +
                "  }\n" +
                "}");
        interpreter.runProgram();
    }
}
