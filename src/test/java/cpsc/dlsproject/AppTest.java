package cpsc.dlsproject;

import autovalue.shaded.com.google$.common.base.$Ascii;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit test for simple App. */
@RunWith(JUnit4.class)
public class AppTest extends TestCase {
  private static final String TEST_DIRECTORY =
      System.getProperty("user.dir") + "/src/test/java/cpsc/dlsproject/cases";
  private static final String TEST_JSON = TEST_DIRECTORY + "/tests.json";
  private static final String TEST_SUITE = "test_suite";
  private static final String CODE = "code";

  @Test
  public void testSuite() throws Exception {
    JSONParser parser = new JSONParser();
    JSONObject jsonObject =
        (JSONObject) parser.parse(new FileReader(Paths.get(TEST_JSON).toString()));
    JSONArray testSuite = (JSONArray) jsonObject.get(TEST_SUITE);

    for (Object obj : testSuite) {
      JSONObject testObj = (JSONObject) obj;
      runTest(testObj);
    }
  }

  private static void runTest(JSONObject testObject) throws Exception {
    String codeFileName = (String) testObject.get(CODE);
    String codePath = TEST_DIRECTORY + "/" + codeFileName;
    String codeString = new String(Files.readAllBytes(Paths.get(codePath)));
    System.out.println(codeString);
    // To run the program and test server here
    Interpreter interpreter = Interpreter.loadScriptFromString(codeString);
    // Uncomment this line to run tests
   // interpreter.runProgram();
    JSONArray testArray = (JSONArray) testObject.get("tests");
    for (Object obj : testArray) {
      JSONObject testObj = (JSONObject) obj;
      String endpoint = (String) testObj.get("endpoint");
      String expectedMessage = (String) testObj.get("expectedMessage");
      // TODO: Test for expectedStatus in the future.
     // testEndpoint(endpoint, expectedMessage);
    }
   // interpreter.killProgram();
    // Sleep for two seconds
   // Thread.sleep(2000);
  }

  private static void testEndpoint(String endpoint, String expectedMessage) throws Exception {
    // Port is 8000 right now temporarily
    URL url = new URL("http://localhost:8000/" + endpoint);
    URLConnection conn = url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    assertEquals(in.readLine(), expectedMessage);
  }
}
