package cpsc.dlsproject;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.visitors.ParseVisitor;
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

        ParseVisitor parser = new ParseVisitor();
        Program program = (Program) parser.run();
//        Program program = new Program();
//        program.parse();

        ServerBuilderVisitor serverBuilder = new ServerBuilderVisitor(program);
        serverBuilder.run();
    }

    public static void main(String[] args) throws Exception {
        Interpreter interpreter = new Interpreter();
        interpreter.loadScriptFromString("GET \"/conditionalTrue\" {\n" +
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
                "}");
        interpreter.runProgram();
    }
}
