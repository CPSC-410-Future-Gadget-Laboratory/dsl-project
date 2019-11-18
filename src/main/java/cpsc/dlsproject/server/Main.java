package cpsc.dlsproject.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public final class Main {
  /** Testing out the Server Class */
  public static void main(String[] args) throws IOException {
    Server server = Server.newBuilder().setPort(8000).build();
//    server
//        .createEndpoint("/")
//        .setHandler(
//            "/",
//            httpExchange -> {
//              byte response[] = "Hello, World!!!".getBytes("UTF-8");
//
//              httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
//              httpExchange.sendResponseHeaders(200, response.length);
//
//              OutputStream out = httpExchange.getResponseBody();
//              out.write(response);
//              out.close();
//            })
//        .startServer();
      server.setupLoggingEndpoint();
      server.startServer();
  }
}
