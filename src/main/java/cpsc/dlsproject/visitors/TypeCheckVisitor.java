package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;

public class TypeCheckVisitor extends ASTVisitor<Boolean> {

    TypeCheckVisitor(Program program) {
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
    Boolean visit(VarAccess varAccess) throws Exception { return null; }

    @Override
    Boolean visit(Conditional conditional) {
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
    Boolean visit(VarDeclaration varDeclaration) {
        return null;
    }

    @Override
    Boolean visit(BinaryOperation binOp) {
        return null;
    }

    @Override
    Boolean visit(NumberValue numVal) throws Exception {
        return null;
    }

    @Override
    Boolean visit(BooleanValue boolVal) throws Exception {
        return null;
    }

    @Override
    Boolean visit(StringValue strVal) throws Exception {
        return null;
    }
}
