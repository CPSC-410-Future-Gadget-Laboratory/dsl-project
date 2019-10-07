package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;

public class PrintVisitor extends ASTVisitor<String> {

    String currIndentation;

    PrintVisitor(Program program) {
        super(program);

        currIndentation = "";
    }

    void scopeIn() {
        currIndentation += "    "; // 4 spaces.
    }

    void scopeOut() {
        currIndentation = currIndentation.substring(0, currIndentation.length() - 4);
    }

    @Override
    String visit(Program program) {
        String output = "";

        for (EndpointDeclaration endpoint: program.endpoints) {
            output += this.visit(endpoint);
        }

        return output;
    }

    @Override
    String visit(EndpointDeclaration endpoint) {
        String output = "";

        output += this.visit(endpoint.requestMethodType) + " {\n";
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
    String visit(Conditional conditional) {
        return null;
    }

    @Override
    String visit(RequestMethod requestMethod) {
        return requestMethod.name();
    }

    @Override
    String visit(Response response) {
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
    String visit(URLDeclaration url) {
        return currIndentation + "ENDPOINT = \"" + url.url + "\";\n";
    }

    @Override
    String visit(ValueDeclaration valueDeclaration) {
        return null;
    }

    @Override
    String visit(BinaryOperation binOp) {
        return null;
    }

    @Override
    String visit(BooleanValue bool) {
        return null;
    }

    @Override
    String visit(NumberValue num) {
        return null;
    }

    @Override
    String visit(StringValue str) {
        return null;
    }
}
