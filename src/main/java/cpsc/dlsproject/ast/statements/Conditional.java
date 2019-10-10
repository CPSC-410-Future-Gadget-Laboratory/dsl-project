package cpsc.dlsproject.ast.statements;

import cpsc.dlsproject.ast.*;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.Statement;

import java.util.ArrayList;

public class Conditional extends Statement {
    public Expression condition;
    public ArrayList<Statement> thenCase;
    public ArrayList<Statement> elseCase;

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
                expression = new VarAccess(tokenizer.getNext());
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

    private IfElse handleIFELSE() {
        if (!tokenizer.getNext().equals("{")) {
            System.out.println("Invalid formation of IFELSE statement");
            System.exit(0);
        }

        IfElse ifelse = new IfElse();
        while(!tokenizer.checkNext().equals("}")) {
            BaseAST currNode = null;
            if (tokenizer.checkToken("VAR")) {
                currNode = new Var();
            } else if (ASTHelpers.CheckForCond()) {
                currNode = new Conditional();
            } else if(tokenizer.checkToken("SEND")){
                currNode = new Send();
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
        if (!tokenizer.getNext().equals("(")) {
            System.out.println("Invalid formation of condition expression");
            System.exit(0);
        }
        expressionHandler();
        IfElse ifStatement = handleIFELSE();
        children.add(ifStatement);
        if(!tokenizer.getNext().equals("ELSE")) {
            System.out.println("Invalid formation of else statement");
            System.exit(0);
        }
        IfElse elseStatement = handleIFELSE();
        children.add(elseStatement);
    }
}
