package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;

public class PrintVisitor extends ASTVisitor<String> {

    String currIndentation;

    public PrintVisitor(Program program) {
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
    String visit(Conditional conditional) throws Exception {
        return null;
    }

    @Override
    String visit(RequestMethod requestMethod) throws Exception {
        return requestMethod.name();
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
        return null;
    }

    @Override
    String visit(BinaryOperation binOp) throws Exception {
        return null;
    }

    @Override
    String visit(BooleanValue bool) throws Exception {
        return null;
    }

    @Override
    String visit(NumberValue num) throws Exception {
        return null;
    }

    @Override
    String visit(StringValue str) throws Exception {
        return null;
    }
}
