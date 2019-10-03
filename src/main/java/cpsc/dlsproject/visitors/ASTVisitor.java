package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;

public abstract class ASTVisitor<X> {
    abstract X visit(Program program);
    abstract X visit(EndpointDeclaration endpoints);
    abstract X visit(Conditional conditional);
    abstract X visit(RequestMethod requestMethod);
    abstract X visit(Response response);
    abstract X visit(URLDeclaration url);
    abstract X visit(ValueDeclaration valueDeclaration);
    abstract X visit(BinaryOperation binOp);
    abstract X visit(BooleanValue bool);
    abstract X visit(NumberValue num);
    abstract X visit(StringValue str);
}

