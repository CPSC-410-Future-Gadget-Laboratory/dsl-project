package cpsc.dlsproject.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Server {
  private final int port;
  private final HttpServer server;
  private final Map<String, HttpContext> endpointMap;
  private final Map<String, Integer> endpointVisitFrequency;
  private final Set<String> reservedEndpoints;
  private final String loggingApiEndpoint = "/logs";

  private Server(int port) throws IOException {
    this.port = port;
    server = HttpServer.create(new InetSocketAddress(port), /* backlog */ 0);
    endpointMap = new HashMap<>();
    endpointVisitFrequency = new HashMap<>();
    reservedEndpoints = new HashSet<>();
    reservedEndpoints.add(loggingApiEndpoint);
  }

  /** Starts a given server */
  public void startServer() {
    server.start();
  }

  /** Gets the port of this server instance */
  public int getPort() {
    return this.port;
  }

  public void stopServer() {
    this.server.stop(0);
  }

  public String getLoggingEndpointUrl() {
    return loggingApiEndpoint;
  }

  /**
   * Creates an endpoint context within the server. If the endpoint has already been created, or the
   * parameter is not a valid path, an {@link IllegalArgumentException} will be thrown
   */
  public Server createEndpoint(String endpoint) {
    if (endpointMap.containsKey(endpoint) || reservedEndpoints.contains(endpoint)) {
      throw new IllegalArgumentException("End point already exists");
    }
    HttpContext context = server.createContext(endpoint);
    endpointMap.put(endpoint, context);
    endpointVisitFrequency.put(endpoint, 0);
    return this;
  }

  /**
   * Sets handler for a specified endpoint. Throws {@link IllegalArgumentException} if the endpoint
   * was not previously created, or if a handler was already set for the endpoint
   */
  public Server setHandler(String endpoint, HttpHandler handler) {
    if (!endpointMap.containsKey(endpoint)) {
      throw new IllegalArgumentException("Endpoint does not :(");
    }
    endpointMap.get(endpoint).setHandler(handler);
    return this;
  }

  /** Returns a builder for this class */
  public static Builder newBuilder() {
    return new Builder();
  }

  public void setupLoggingEndpoint() {
    HttpHandler handler =
        (httpExchange) -> {
          this.increaseEndpointHitFrequency(this.getLoggingEndpointUrl());
          StringBuilder response = new StringBuilder();
          Set<Map.Entry<String, Integer>> frequencySet = this.getEndpointFrequencyEntrySet();
          for (Map.Entry<String, Integer> entry : frequencySet) {
            response.append(entry.getKey()).append(":").append(entry.getValue()).append(System.getProperty("line.separator"));
          }
          httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
          httpExchange.sendResponseHeaders(200, response.length());
          OutputStream out = httpExchange.getResponseBody();
          out.write(response.toString().getBytes());
          out.close();
        };
    this.endpointMap.put(
        this.loggingApiEndpoint, this.server.createContext(this.loggingApiEndpoint));
    this.setHandler(this.getLoggingEndpointUrl(), handler);
    System.out.println("Set handler for logging");
    this.endpointVisitFrequency.put(this.loggingApiEndpoint, 0);
  }

  /** Increase the frequency hit of the endpoint */
  public void increaseEndpointHitFrequency(String endpoint) {
    if (!endpointVisitFrequency.containsKey(endpoint)) {
      throw new IllegalArgumentException("Frequency increasing method: Endpoint does not exist");
    }
    endpointVisitFrequency.put(endpoint, endpointVisitFrequency.get(endpoint) + 1);
  }

  /** Returns endpoint entry set */
  private Set<Map.Entry<String, Integer>> getEndpointFrequencyEntrySet() {
    return endpointVisitFrequency.entrySet();
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
