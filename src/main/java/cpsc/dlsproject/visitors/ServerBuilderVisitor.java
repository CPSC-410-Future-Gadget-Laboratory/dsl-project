package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.visitors.ASTVisitor;

/**
 * A class representing a visitor for building the server.
 */
public class ServerBuilderVisitor extends ASTVisitor<Boolean> {

    ServerBuilderVisitor(Program program) {
        super(program);
    }

    @Override
    Boolean visit(Program program) {
        return null;
    }

    @Override
    Boolean visit(EndpointDeclaration endpoints) {
        return null;
    }

    @Override
    Boolean visit(Conditional conditional) {
        return null;
    }

    @Override
    Boolean visit(RequestMethod requestMethod) {
        return null;
    }

    @Override
    Boolean visit(Response response) {
        return null;
    }

    @Override
    Boolean visit(URLDeclaration url) {
        return null;
    }

    @Override
    Boolean visit(ValueDeclaration valueDeclaration) {
        return null;
    }

    @Override
    Boolean visit(BinaryOperation binOp) {
        return null;
    }

    @Override
    Boolean visit(BooleanValue bool) {
        return null;
    }

    @Override
    Boolean visit(NumberValue num) {
        return null;
    }

    @Override
    Boolean visit(StringValue str) {
        return null;
    }
}
