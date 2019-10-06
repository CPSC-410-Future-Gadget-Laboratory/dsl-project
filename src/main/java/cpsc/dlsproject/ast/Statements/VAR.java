package cpsc.dlsproject.ast.Statements;

import cpsc.dlsproject.ast.ASTHelpers;
import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.Expressions.BinaryOperation;
import cpsc.dlsproject.ast.Expressions.Expression;
import cpsc.dlsproject.ast.Expressions.VARACCESS;
import cpsc.dlsproject.ast.Expressions.Values.BooleanValue;
import cpsc.dlsproject.ast.Expressions.Values.NumberValue;
import cpsc.dlsproject.ast.Expressions.Values.StringValue;
import cpsc.dlsproject.ast.Expressions.Values.Value;

public class VAR extends BaseAST {
    public String name;
    public Expression expression;

    private BinaryOperation handleOper(BinaryOperation operation, Expression expression) {
        BinaryOperation oper = operation;
        if (oper == null) {
            oper = new BinaryOperation(tokenizer.getNext());
            oper.lhs = expression;
        } else {
            oper.rhs = expression;
        }
        return oper;
    }

    private StringValue handleString() {
        tokenizer.getAndCheckNext("\"");
        StringValue stringValue = new StringValue(tokenizer.getNext());
        tokenizer.getAndCheckNext("\"");
        return stringValue;
    }

    private void expressionHandler() {
        BinaryOperation operation = null;
        Expression expression;
        while (!tokenizer.checkNext().equals(";")) {
            String token = tokenizer.checkNext();
            if (token.matches("\"")) {
                expression = handleString();
            } else if (token.equals("true") || token.equals("false")) {
                expression = new BooleanValue(Boolean.parseBoolean(tokenizer.getNext()));
            } else if (token.matches("^\\((?=.)([+-]?([0-9]*)(\\.([0-9]+))?)\\)$") || token.matches("^[-+]?\\d+$")) {
                expression = new NumberValue(Double.parseDouble(tokenizer.getNext()));
            } else {
                expression = new VARACCESS(tokenizer.getNext());
            }
            operation = handleOper(operation, expression);
        }
        if (operation == null) {
            System.out.println("Error in expression creation in AST. EXIT");
            System.exit(0);
        } else {
            this.expression = operation;
        }
    }

    @Override
    public void parse() {
//        tokenizer.getAndCheckNext("\\{");
        name = tokenizer.getNext();
        if (tokenizer.checkNext().equals("String")) {
            tokenizer.getNext();
            if (tokenizer.checkNext().matches("\"") && tokenizer.checkAheadOfNext(3).equals(";")) {
                expression = handleString();
            } else {
                expressionHandler();
            }
        } else if (tokenizer.checkNext().equals("Number")) {
            tokenizer.getNext();
            if (tokenizer.checkAheadOfNext(1).equals(";")) {
                expression = new NumberValue(Double.parseDouble(tokenizer.getNext()));
            } else {
                expressionHandler();
            }
        } else if (tokenizer.checkNext().equals("Boolean")) {
                tokenizer.getNext();
            if (tokenizer.checkAheadOfNext(1).equals(";")) {
                expression = new BooleanValue(Boolean.parseBoolean(tokenizer.getNext()));
            } else {
                expressionHandler();
            }
        }
        tokenizer.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }
}
