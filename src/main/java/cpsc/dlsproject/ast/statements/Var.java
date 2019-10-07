package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;

public class Var extends BaseAST {
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
                expression = new VarAccess(tokenizer.getNext());
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
}
