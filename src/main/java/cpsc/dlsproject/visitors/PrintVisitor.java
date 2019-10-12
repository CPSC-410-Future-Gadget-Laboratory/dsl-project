package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.BinaryOperator;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;

import java.util.HashMap;

public class PrintVisitor extends ASTVisitor<String> {
    private final static int INDENTATION_SIZE = 4;
    private String currIndentation;

    public PrintVisitor(Program program) {
        super(program);

        currIndentation = "";
    }

    void scopeIn() {
        for (int i = 0; i < INDENTATION_SIZE; i++) {
            currIndentation += " "; // 4 spaces.
        }
    }

    void scopeOut() {
        currIndentation = currIndentation.substring(0, currIndentation.length() - INDENTATION_SIZE);
    }

    @Override
    String visit(Program program) throws Exception {
        String output = "";

        for (EndpointDeclaration endpoint: program.endpoints) {
            output += this.visit(endpoint);
        }

        return output;
    }

    @Override
    String visit(EndpointDeclaration endpoint) throws Exception {
        String output = "";

        output += endpoint.requestMethodType.name() + " {\n";
        this.scopeIn();
        output += this.visit(endpoint.url);

        for(Statement statement : endpoint.statements) {
            output += this.visit(statement);
        }
        this.scopeOut();
        output += this.currIndentation + "};\n";

        return output;
    }

    @Override
    String visit(VarAccess varAccess) throws Exception {
        return varAccess.identifier;
    }

    @Override
    String visit(Conditional conditional) throws Exception {
        String output = "";

        output += currIndentation + "IF (" + this.visit(conditional.condition) + ") {\n";
        this.scopeIn();
        for(Statement statement: conditional.thenCase) {
            output += this.visit(statement);
        }

        if (conditional.elseCase != null) {
            this.scopeOut();
            output += currIndentation + "} else {\n";
            this.scopeIn();

            for(Statement statement: conditional.elseCase) {
                output += this.visit(statement);
            }
        }

        this.scopeOut();
        output += currIndentation + "}\n";

        return output;
    }

    @Override
    String visit(Response response) throws Exception {
        String output = "";

        output += currIndentation + "SEND = {\n";
        this.scopeIn();
        output += currIndentation + response.statusCode + "\";\n";
        output += currIndentation + "\"" + response.message + "\";\n";
        this.scopeOut();
        output += currIndentation + "};\n";

        return output;
    }

    @Override
    String visit(URLDeclaration url) throws Exception {
        return currIndentation + "ENDPOINT = \"" + url.url + "\";\n";
    }

    @Override
    String visit(ValueDeclaration valueDeclaration) throws Exception {
        return currIndentation + "VAL " + valueDeclaration.name + ": " + valueDeclaration.type.name() + " = " + this.visit(valueDeclaration.expression) + ";\n";
    }

    @Override
    String visit(BinaryOperation binOp) throws Exception {
        HashMap<BinaryOperator, String> binSymbolMap = new HashMap<BinaryOperator, String>();

        binSymbolMap.put(BinaryOperator.PLUS, "+");
        binSymbolMap.put(BinaryOperator.MINUS, "-");
        binSymbolMap.put(BinaryOperator.MULTIPLY, "*");
        binSymbolMap.put(BinaryOperator.DIVISION, "\\");
        binSymbolMap.put(BinaryOperator.LESSER, "<");
        binSymbolMap.put(BinaryOperator.LEQUAL, "<=");
        binSymbolMap.put(BinaryOperator.EQUAL, "==");
        binSymbolMap.put(BinaryOperator.NOTEQUAL, "!=");
        binSymbolMap.put(BinaryOperator.GEQUAL, ">=");
        binSymbolMap.put(BinaryOperator.GREATER, ">");
        binSymbolMap.put(BinaryOperator.AND, "&&");
        binSymbolMap.put(BinaryOperator.OR, "||");

        return "(" + this.visit(binOp.lhs) + " " + binSymbolMap.get(binOp.operator) + " " + this.visit(binOp.rhs) + ")";
    }
}
