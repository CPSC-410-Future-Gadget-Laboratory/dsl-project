package cpsc.dlsproject.server;

import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {
    private static Server server;

    @BeforeClass
    public static void setup() throws Exception {
        server = Server.newBuilder().setPort(8000).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createDuplicateEndpoints() {
        String mockEndpoint = "/mock";
        server.createEndpoint(mockEndpoint);
        server.createEndpoint(mockEndpoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidEndpoint() {
        String invalidEndpoint = "invalid";
        server.createEndpoint(invalidEndpoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHandlerOnNonExistentEndpoint() {
        server.setHandler("/nonExistent", httpExchange -> {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMultipleHandlers() {
        String endpoint = "/";
        server.setHandler(endpoint, httpExchange -> {});
        server.setHandler(endpoint, httpExchange -> {});
    }

}
