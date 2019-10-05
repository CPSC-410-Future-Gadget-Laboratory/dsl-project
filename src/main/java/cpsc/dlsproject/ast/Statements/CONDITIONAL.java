package cpsc.dlsproject.ast.Statements;


import cpsc.dlsproject.ast.*;
import cpsc.dlsproject.ast.Expressions.BinaryOperation;
import cpsc.dlsproject.ast.Expressions.Expression;
import cpsc.dlsproject.ast.Expressions.VARACCESS;
import cpsc.dlsproject.ast.Expressions.Values.BooleanValue;
import cpsc.dlsproject.ast.Expressions.Values.NumberValue;
import cpsc.dlsproject.ast.Expressions.Values.StringValue;

public class CONDITIONAL extends STATEMENT {
    public Expression condition;

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

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
        while (!tokenizer.checkNext().equals(")")) {
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
            this.condition = operation;
        }
        tokenizer.getNext(); // Pop the end )
    }

    private IFELSE handleIFELSE() {
        if (!tokenizer.getNext().equals("{")) {
            System.out.println("Invalid formation of conditional statement");
            System.exit(0);
        }

        IFELSE ifelse = new IFELSE();
        while(!tokenizer.checkNext().equals("}")) {
            BaseAST currNode = null;
            if (tokenizer.checkToken("VAR")) {
                currNode = new VAR();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new CONDITIONAL();
            } else if(tokenizer.checkToken("SEND")){
                currNode = new SEND();
            }
            if (currNode == null) {
                System.out.println("Error, invalid token in conditional statement");
                System.exit(0);
            } else {
                tokenizer.getNext(); // Pop the keyword
                currNode.parse();
                ifelse.children.add(currNode);
            }
        }
        tokenizer.getNext(); // Pop end token
        return ifelse;
    }

    @Override
    public void parse() {
        if (!tokenizer.getNext().equals("{")) {
            System.out.println("Invalid formation of conditional statement");
            System.exit(0);
        }
        if (!tokenizer.getNext().equals("(")) {
            System.out.println("Invalid formation of condition expression");
            System.exit(0);
        }
        expressionHandler();
        IFELSE ifStatement = handleIFELSE();
        children.add(ifStatement);
        if(tokenizer.getNext().equals("ELSE")) {
            System.out.println("Invalid formation of else statement");
            System.exit(0);
        }
        IFELSE elseStatement = handleIFELSE();
        children.add(elseStatement);
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
