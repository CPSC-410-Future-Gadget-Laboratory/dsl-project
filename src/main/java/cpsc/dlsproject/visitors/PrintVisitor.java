package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;

public class PrintVisitor extends ASTVisitor<String> {
    @Override
    String visit(Program program) {
        String output = "";

        for (EndpointDeclaration endpoint: program.endpoints) {
            output += "Hellow!";
        }

        return output;
    }

    @Override
    String visit(EndpointDeclaration endpoints) {
        return null;
    }

    @Override
    String visit(Conditional conditional) {
        return null;
    }

    @Override
    String visit(RequestMethod requestMethod) {
        return null;
    }

    @Override
    String visit(Response response) {
        return null;
    }

    @Override
    String visit(URLDeclaration url) {
        return null;
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
