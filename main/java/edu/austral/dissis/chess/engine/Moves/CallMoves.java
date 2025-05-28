package edu.austral.dissis.chess.engine.Moves;

import java.util.HashMap;
import java.util.Map;

public class CallMoves {
  private final Map<String, validMoves> movesMap = new HashMap<>();
  private final String basePackage = "edu.austral.dissis.chess.engine.Moves";

  public validMoves getMoves(String name) {
    if (movesMap.containsKey(name)) {
      return movesMap.get(name);
    }
    try {
      String className = basePackage + "." + name;
      Class<?> clazz = Class.forName(className);
      validMoves command = (validMoves) clazz.getDeclaredConstructor().newInstance();
      movesMap.put(name, command);
      return command;
    } catch (Exception e) {
      return null;
    }
  }
}
