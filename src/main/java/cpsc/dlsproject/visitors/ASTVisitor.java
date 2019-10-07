package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.ast.Program;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ASTVisitor<X> {
    Program program;

    ASTVisitor(Program program) {
        this.program = program;
    }

    X run() {
        return this.visit(program);
    }

    X visit(Statement statement) {
        Class subClass = statement.getClass();

        Method actualClassVisitMethod = null;
        try {
            actualClassVisitMethod = this.getClass().getDeclaredMethod("visit", subClass);
            return (X) actualClassVisitMethod.invoke(this, statement);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    X visit(Expression expression) {
        return this.visit(expression.getClass().cast(expression));
    }

    abstract X visit(Program program);
    abstract X visit(EndpointDeclaration endpoint);
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

