package cpsc.dlsproject.tools;

import java.util.HashMap;
import java.util.Map;

public class VariableMap {

  private static final Map<String, Variable> variables = new HashMap<>();

  /** Adds a name -> variable mapping to the global variable map. Overrides if name already exists */
  public static void addVariable(String name, Variable variable) {
    variables.put(name, variable);
  }

  /** Returns a variable with a particular name */
  public static Variable getVariable(String name) {
    return variables.get(name);
  }

  public static boolean variableExists(String name) {
      return variables.containsKey(name);
  }
}
