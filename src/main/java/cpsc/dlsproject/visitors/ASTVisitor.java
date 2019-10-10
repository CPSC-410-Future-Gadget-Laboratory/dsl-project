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

    public X run() {
        try {
            return this.visit(program);
        } catch (Exception e) {
            System.out.println("Error visiting ASTs.");
            e.printStackTrace();
        }
        return null;
    }

    X visit(Statement statement) throws Exception {
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

    X visit(Expression expression) throws Exception {
        Class subClass = expression.getClass();

        Method actualClassVisitMethod = null;
        try {
            actualClassVisitMethod = this.getClass().getDeclaredMethod("visit", subClass);
            return (X) actualClassVisitMethod.invoke(this, expression);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    abstract X visit(Program program) throws Exception;
    abstract X visit(EndpointDeclaration endpoint) throws Exception;
    abstract X visit(Conditional conditional) throws Exception;
    abstract X visit(RequestMethod requestMethod) throws Exception;
    abstract X visit(Response response) throws Exception;
    abstract X visit(URLDeclaration url) throws Exception;
    abstract X visit(ValueDeclaration valueDeclaration) throws Exception;
    abstract X visit(BinaryOperation binOp) throws Exception;
    abstract X visit(BooleanValue bool) throws Exception;
    abstract X visit(NumberValue num) throws Exception;
    abstract X visit(StringValue str) throws Exception;
}

