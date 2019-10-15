package cpsc.dlsproject.visitors;

import cpsc.dlsproject.ast.ASTHelpers;
import cpsc.dlsproject.ast.BaseAST;
import cpsc.dlsproject.ast.Program;
import cpsc.dlsproject.ast.expressions.BinaryOperation;
import cpsc.dlsproject.ast.expressions.BinaryOperator;
import cpsc.dlsproject.ast.expressions.Expression;
import cpsc.dlsproject.ast.expressions.VarAccess;
import cpsc.dlsproject.ast.expressions.values.BooleanValue;
import cpsc.dlsproject.ast.expressions.values.NumberValue;
import cpsc.dlsproject.ast.expressions.values.StringValue;
import cpsc.dlsproject.ast.statements.*;
import cpsc.dlsproject.tools.Tokenizer;
import cpsc.dlsproject.types.Type;
import cpsc.dlsproject.utils.Utils;

import java.util.ArrayList;

public class ParseVisitor extends ASTVisitor<BaseAST> {
    private Tokenizer tokenizer;
    private Utils utils = Utils.getUtilsObj();
    private String currentExpression;

    ParseVisitor(Program program) {
        super(program);
    }

    public ParseVisitor() {
        tokenizer = Tokenizer.getTokenizer();
    }

    @Override
    public BaseAST run() throws Exception {
        Program program = new Program();
        return this.visit(program);
    }

    @Override
    BaseAST visit(Program program) throws Exception {
        program.endpoints = new ArrayList<EndpointDeclaration>();
        while (tokenizer.moreTokens()) {
            EndpointDeclaration endpointDeclaration = null;
            if (ASTHelpers.CheckForRequestType()) {
                endpointDeclaration = new EndpointDeclaration();
            }
            if (endpointDeclaration == null) {
                throw new Exception("Cannot parse endpoint.");
            }
            endpointDeclaration = (EndpointDeclaration) this.visit(endpointDeclaration);
            program.endpoints.add(endpointDeclaration);
            tokenizer.getAndCheckNext("}");
        }

        return program;
    }

    @Override
    BaseAST visit(EndpointDeclaration endpoint) throws Exception {
        String requestType = tokenizer.getNext();

        switch (requestType) {
            case "GET":
                endpoint.requestMethodType = RequestMethod.GET;
                break;

            case "POST":
                endpoint.requestMethodType = RequestMethod.POST;
                break;

            case "PUT":
                endpoint.requestMethodType = RequestMethod.PUT;
                break;

            case "DELETE":
                endpoint.requestMethodType = RequestMethod.DELETE;
                break;

            default:
                throw new Exception("Request endpoint must use keyword GET, POST, PUT, DELETE.");
        }

        URLDeclaration url = new URLDeclaration();
        endpoint.url = (URLDeclaration) this.visit(url);
        endpoint.statements = new ArrayList<Statement>();

        tokenizer.getAndCheckNext("\\{");

        endpoint.statements = this.parseStatements();

        return endpoint;
    }


    Expression parseExpression() throws Exception {
        String token = tokenizer.checkCurrent();
        if (utils.checkIfTokenIsOperator(token)) {
            BinaryOperation binOp = new BinaryOperation();
            return (Expression) this.visit(binOp);
        } else if (utils.checkIfTokenIsNumber(token)) {
            return new NumberValue(Double.parseDouble(tokenizer.getNext()));
        } else if (utils.checkIfTokenIsString(token)) {
            return new StringValue(tokenizer.getNext());
        } else if (token.equals("true") || token.equals("false")) {
            return new BooleanValue(tokenizer.getNext().equals("true"));
        } else {
            return (Expression) this.visit(new VarAccess());
        }
    }

    private ArrayList<Statement> parseStatements() throws Exception {
        ArrayList<Statement> statements = new ArrayList<Statement>();
        while (!tokenizer.checkCurrent().equals("}")) {
            switch (tokenizer.checkCurrent()) {
                case "SEND":
                    statements.add((Statement) this.visit(new Response()));
                    break;

                case "VAR":
                    statements.add((Statement) this.visit(new VarDeclaration()));
                    break;

                case "IF":
                    statements.add((Conditional) this.visit(new Conditional()));
                    break;

                default:
                    throw new Exception("Invalid statement in body of endpoint declaration.");
            }
            if (tokenizer.checkCurrent().equals(";")) {
                tokenizer.getAndCheckNext(";");
            }
        }
        return statements;
    }

    @Override
    BaseAST visit(VarAccess varAccess) throws Exception {
        varAccess.identifier = tokenizer.getNext();
        return varAccess;
    }

    @Override
    BaseAST visit(Conditional conditional) throws Exception {
        // TODO: Add logic for expression.
        tokenizer.getAndCheckNext("IF");
        tokenizer.getAndCheckNext("\\(");
        conditional.condition = parseExpression();
        tokenizer.getAndCheckNext("\\)");
        tokenizer.getAndCheckNext("\\{");
        conditional.thenCase = parseStatements();
        tokenizer.getAndCheckNext("\\}");
        if (tokenizer.checkNext().equals("ELSE")) {
            tokenizer.getAndCheckNext("ELSE");
            tokenizer.getAndCheckNext("\\{");
            conditional.elseCase = parseStatements();
            tokenizer.getAndCheckNext("\\}");
        }

        return conditional;
    }

    @Override
    BaseAST visit(Response response) throws Exception {
        tokenizer.getAndCheckNext("SEND");
        tokenizer.getAndCheckNext("\\{");
        response.statusCode = Integer.parseInt(tokenizer.getNext());
        tokenizer.getAndCheckNext(";");
        response.message = tokenizer.getNext();
        tokenizer.getAndCheckNext(";");
        tokenizer.getAndCheckNext("\\}");
        return response;
    }

    @Override
    BaseAST visit(URLDeclaration url) throws Exception {
        String urlString = tokenizer.getNext();
        if (!utils.checkIfTokenIsString(urlString)) {
            throw new Exception("url is not string");
        }

        url.url = utils.getStringLiteralContent(urlString);
        return url;
    }

    @Override
    BaseAST visit(VarDeclaration varDeclaration) throws Exception {
        tokenizer.getAndCheckNext("VAR");
        String name = tokenizer.getNext();
        if (name.charAt(name.length() - 1) != ':') {
            throw new Exception("variable declaration must have colon to separate identifier and the type.");
        }
        varDeclaration.name = name.substring(0, name.length() - 1);

        String type = tokenizer.getNext();

        switch (type) {
            case "Number":
                varDeclaration.type = Type.NUMBER;
                varDeclaration.expression = new NumberValue(Double.parseDouble(tokenizer.getNext()));
                break;

            case "Boolean":
                varDeclaration.type = Type.BOOLEAN;
                String token = tokenizer.getNext();
                if (!token.equals("true") && !token.equals("false")) {
                    throw new Exception("Boolean type is not either true or false.");
                }
                varDeclaration.expression = new BooleanValue(token.equals("true"));
                break;

            default:
                throw new Exception("Invalid type in Var declaration.");

        }

//        List<String> expressionList = tokenizer.getNextExpression();
//
//
//        String expression = InfixToPrefixConverter.infixToPrefix(expressionList);
//        currentExpression = expression;
//
//        System.out.println(expression);
//
//        int i = 0;
//        while(i < expression.length()) {
//            if (utils.checkIfTokenIsNumber(expression.charAt(i) + "")) {
//                String numberStr = "";
//                while (i < expression.length() && (utils.checkIfTokenIsNumber(expression.charAt(i) + "") || expression.charAt(i) == '.')) {
//                    numberStr += expression.charAt(i);
//                    i++;
//                }
//                currentExpression = currentExpression;
//            }
//            i++;
//        }

        return varDeclaration;
    }

    @Override
    BaseAST visit(BinaryOperation binOp) throws Exception {
        String token = tokenizer.getNext();
        if (token.equals("+")) {
            binOp.operator = BinaryOperator.PLUS;
        } else if (token.equals("-")) {
            binOp.operator = BinaryOperator.MINUS;
        } else if (token.equals("*")) {
            binOp.operator = BinaryOperator.MULTIPLY;
        } else if (token.equals("\\")) {
            binOp.operator = BinaryOperator.DIVISION;
        } else if (token.equals("%")) {
        } else if (token.equals("<")) {
            binOp.operator = BinaryOperator.LESSER;
        } else if (token.equals("<=")) {
            binOp.operator = BinaryOperator.LEQUAL;
        } else if (token.equals("==")) {
            binOp.operator = BinaryOperator.EQUAL;
        } else if (token.equals("!=")) {
            binOp.operator = BinaryOperator.NOTEQUAL;
        } else if (token.equals(">=")) {
            binOp.operator = BinaryOperator.GEQUAL;
        } else if (token.equals(">")) {
            binOp.operator = BinaryOperator.GREATER;
        } else {
            throw new Exception(token + " cannot be parsed as expression.");
        }

        binOp.lhs = parseExpression();
        tokenizer.getAndCheckNext("TO");
        binOp.rhs = parseExpression();
        return binOp;
    }

    @Override
    BaseAST visit(NumberValue numVal) throws Exception {
        throw new Exception("Not Implemented.");
    }

    @Override
    BaseAST visit(BooleanValue boolVal) throws Exception {
        throw new Exception("Not Implemented.");
    }

    @Override
    BaseAST visit(StringValue strVal) throws Exception {
        throw new Exception("Not Implemented.");
    }
}
