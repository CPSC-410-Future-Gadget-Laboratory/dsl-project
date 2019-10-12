package cpsc.dlsproject;

import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit test for simple App. */
@RunWith(JUnit4.class)
public class AppTest extends TestCase {
  private static final String TEST_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/cpsc/dlsproject/cases";
  private static final String TEST_JSON = TEST_DIRECTORY + "/tests.json";
  private static final String TEST_SUITE = "test_suite";
  private static final String CODE = "code";

  @Test
  public void testSuite() throws Exception {
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(Paths.get(TEST_JSON).toString()));
    JSONArray testSuite = (JSONArray) jsonObject.get(TEST_SUITE);

    for (Object obj: testSuite) {
        JSONObject testObj = (JSONObject) obj;
        runTest(testObj);
    }
  }

  private static void runTest(JSONObject testObject) throws Exception {
      String codeFileName = (String) testObject.get(CODE);
      String codePath = TEST_DIRECTORY + "/" + codeFileName;
      String codeString = new String(Files.readAllBytes(Paths.get(codePath)));
      // To run the program and test server here
      // Interpreter.loadScriptFromString(codeString).runProgram();

  }
}

//        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//        server.createContext("/test", httpExchange -> {
//           byte[] response = "hello".getBytes();
//            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
//           httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
//            OutputStream out = httpExchange.getResponseBody();
//            out.write(response);
//            out.close();
//        });
//        server.start();
//
//        URL url = new URL("http://localhost:8080/test");
//        URLConnection conn = url.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        System.out.println("The stuff I am receiving is:");
//        System.out.println(in.readLine());
