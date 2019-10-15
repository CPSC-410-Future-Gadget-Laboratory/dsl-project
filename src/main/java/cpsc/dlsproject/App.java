package cpsc.dlsproject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Hello world! */
public class App {
  private static final String DIRECTORY =
          System.getProperty("user.dir") + "/src/main/java/cpsc/dlsproject/";
  public static void main(String[] args) {
    System.out.println("Reading your program!");
    String program;
    try {
      program = new String(Files.readAllBytes(Paths.get(DIRECTORY + args[0])), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Cannot read file because " + e);
    }
    try {
      Interpreter.loadScriptFromString(program).runProgram();
    } catch (Exception e) {
      throw new RuntimeException("Issues with running the program: " + e);
    }
  }
}
