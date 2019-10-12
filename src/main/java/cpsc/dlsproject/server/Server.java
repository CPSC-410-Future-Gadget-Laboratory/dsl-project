package cpsc.dlsproject.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public final class Server {
  private final int port;
  private final HttpServer server;
  private final Map<String, HttpContext> endpointMap;

  private Server(int port) throws IOException {
    this.port = port;
    server = HttpServer.create(new InetSocketAddress(port), /* backlog */ 0);
    endpointMap = new HashMap<>();
  }

  /** Starts a given server */
  public void startServer() {
    server.start();
  }

  /** Gets the port of this server instance */
  public int getPort() {
    return this.port;
  }

  /**
   * Creates an endpoint context within the server. If the endpoint has already been created, or the
   * parameter is not a valid path, an {@link IllegalArgumentException} will be thrown
   */
  public Server createEndpoint(String endpoint) {
    if (endpointMap.containsKey(endpoint)) {
      throw new IllegalArgumentException("End point already exists");
    }
    HttpContext context = server.createContext(endpoint);
    endpointMap.put(endpoint, context);
    return this;
  }

  /**
   * Sets handler for a specified endpoint. Throws {@link IllegalArgumentException} if the endpoint
   * was not previously created, or if a handler was already set for the endpoint
   */
  public Server setHandler(String endpoint, HttpHandler handler) {
    if (!endpointMap.containsKey(endpoint)) {
      throw new IllegalArgumentException("Endpoint does not exist");
    }
    endpointMap.get(endpoint).setHandler(handler);
    return this;
  }

  /** Returns a builder for this class */
  public static Builder newBuilder() {
    return new Builder();
  }

  /** Builder class for server */
  public static final class Builder {
    // Default port value
    private int port = 8080;

    public Builder setPort(int port) {
      this.port = port;
      return this;
    }

    public Server build() throws IOException {
      return new Server(port);
    }
  }
}
