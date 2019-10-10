//package cpsc.dlsproject.visitors;
//
//import com.sun.net.httpserver.HttpExchange;
//import cpsc.dlsproject.ast.Program;
//import cpsc.dlsproject.ast.expressions.BinaryOperation;
//import cpsc.dlsproject.ast.expressions.values.BooleanValue;
//import cpsc.dlsproject.ast.expressions.values.NumberValue;
//import cpsc.dlsproject.ast.expressions.values.StringValue;
//import cpsc.dlsproject.ast.expressions.values.Value;
//import cpsc.dlsproject.ast.statements.*;
//import cpsc.dlsproject.server.Server;
//import org.graalvm.compiler.hotspot.HotSpotTTYStreamProvider;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@FunctionalInterface
//interface ExecutableThunk {
//    /**
//     * Executes the thunk given the context,
//     * @param httpExchange the current httpExchange if statement is inside endpoint body, null otherwise.
//     * @return Value if Thunk is an expression, null otherwise.
//     */
//    Value execute(HttpExchange httpExchange);
//}
//
//public class ServerLambdaImplementationVisitor extends ASTVisitor<ExecutableThunk> {
//    private SymbolTable variables;
//    private Server server;
//
//    ServerLambdaImplementationVisitor(Program program) throws IOException {
//        super(program);
//        variables = new SymbolTable();
//        server = Server.newBuilder().setPort(8080).build();
//    }
//
//    @Override
//    ExecutableThunk visit(Program program) throws Exception {
//        ArrayList<ExecutableThunk> instructions = new ArrayList<ExecutableThunk>();
//        for (EndpointDeclaration endpoint : program.endpoints) {
//            instructions.add(this.visit(endpoint));
//        }
//
//        return (HttpExchange nullHttpExchange) -> {
//            for (ExecutableThunk instruction : instructions) {
//                instruction.execute(nullHttpExchange);
//            }
//
//            return null;
//        };
//    }
//
//    @Override
//    ExecutableThunk visit(EndpointDeclaration endpoint) throws Exception {
//        ArrayList<ExecutableThunk> instructions = new ArrayList<ExecutableThunk>();
//        instructions.add(this.visit(endpoint.url));
//
//        // Prepare instructions thunk.
//        ArrayList<ExecutableThunk> handlerInstructions = new ArrayList<ExecutableThunk>();
//        for (Statement statement : endpoint.statements) {
//            handlerInstructions.add(this.visit(statement));
//        }
//
//        instructions.add((HttpExchange nullHttpExchange) -> {
//            server.setHandler(endpoint.url.url, httpExchange -> {
//                for (ExecutableThunk handlerInstruction : handlerInstructions) {
//                    handlerInstruction.execute(httpExchange);
//                }
//
//                return;
//            });
//
//            return null;
//        });
//
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(Conditional conditional) throws Exception {
//        ArrayList<ExecutableThunk> instructions = new ArrayList<ExecutableThunk>();
//
//        instructions.add(this.visit(conditional.condition));
//
//        instructions.add((HttpExchange httpExchange) -> {
//           return this.visit(conditional.condition);
//        });
//    }
//
//    @Override
//    ExecutableThunk visit(RequestMethod requestMethod) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(Response response) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(URLDeclaration url) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(ValueDeclaration valueDeclaration) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(BinaryOperation binOp) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(BooleanValue bool) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(NumberValue num) throws Exception {
//        return null;
//    }
//
//    @Override
//    ExecutableThunk visit(StringValue str) throws Exception {
//        return null;
//    }
//}
