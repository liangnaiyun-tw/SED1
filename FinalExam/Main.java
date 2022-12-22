import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

  private static Map<String, Integer> levels = new LinkedHashMap<>();

  private static void parseCommandAddStudent(ReviewSystem reviewSystem, String[] inputTokens) {
    for (int i = 1; i < inputTokens.length; ++i) {
      try {
        reviewSystem.addStudent(inputTokens[i]);
      } catch (Exception error) {
        System.out.println(error.getMessage());
      }
    }
  }

  private static void parseCommandAddLevels(String[] inputTokens) {
    String[] tokens;

    for (int i = 1; i < inputTokens.length; ++i) {
      try {
        tokens = inputTokens[i].split(",");
        if (tokens.length != 2) {
          throw new Exception("Error");
        }
        levels.put(tokens[0], Integer.parseInt(tokens[1]));
      } catch (Exception error) {
        System.out.println(error.getMessage());
      }
    }
  }

  private static void parseCommandAddRubric(ReviewSystem reviewSystem, String[] inputTokens) {
    String line;
    String[] tokens;
    BufferedReader criterionBuffer;

    try {
      criterionBuffer = new BufferedReader(new FileReader(inputTokens[2]));

      RubricBuilder builder = new RubricBuilder();
      for (Entry<String, Integer> level : levels.entrySet()) {
        builder.addLevel(level.getKey(), level.getValue());
      }

      while ((line = criterionBuffer.readLine()) != null) {
        try {
          tokens = line.split("\\t+");
          if (tokens.length != 3 || tokens[2].isEmpty()) {
            throw new Exception("Error");
          }

          builder.addCriterion(tokens[0], tokens[1], tokens[2]);
        } catch (Exception criterionError) {
          System.out.println(criterionError.getMessage());
        }
      }

      reviewSystem.addAssignment(inputTokens[1], builder.build());
    } catch (Exception fileError) {
      System.out.println("Error");
    }
  }

  private static void parseCommandAddReview(ReviewSystem reviewSystem, String[] inputTokens) {
    String line;
    String[] tokens;
    List<BufferedReader> reviewFileBuffer = new ArrayList<>();

    try {
      for (int i = 3; i < inputTokens.length; ++i) {
        tokens = inputTokens[i].split(",");
        if (tokens.length != 2) {
          throw new Exception("Error");
        }
        if (tokens[0].equals(inputTokens[2])) {
          throw new Exception("Cannot review one's own assignment.");
        }
      }

      if (inputTokens.length < 6 || inputTokens.length > 8) {
        throw new Exception("Assignment should be reviewed by 3-5 students.");
      }
    } catch (Exception reviewerError) {
      System.out.println(reviewerError.getMessage());
      return;
    }

    StringBuilder result = new StringBuilder();

    try {
      for (int i = 3; i < inputTokens.length; ++i) {
        tokens = inputTokens[i].split(",");
        reviewFileBuffer = new BufferedReader(new FileReader(tokens[1]));

        List<String> scores = new ArrayList<>();
        while ((line = reviewFileBuffer.readLine()) != null) {
          scores.add(line);
        }

        result.append(reviewSystem.addReview(inputTokens[1], tokens[0], inputTokens[2], scores))
            .append("\n");
        System.out.print(result);
      }
    } catch (Exception error) {
      System.out.println("Error");
    }
  }

  private static void parseCommandPrintRubric(ReviewSystem reviewSystem, String[] inputTokens) {
    try {
      reviewSystem.printRubric(inputTokens[1]);
    } catch (Exception printError) {
      System.out.println(printError.getMessage());
    }
  }

  private static void parseCommandAverageCriterion(ReviewSystem reviewSystem,
      String[] inputTokens) {
    try {
      reviewSystem.averageCriterion(inputTokens[1]);
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }

  private static RankingStrategy getRankingStrategy(String strategy) throws Exception {
    if (strategy.equals("MeanRankingStrategy")) {
      return new MeanRankingStrategy();
    } else if (strategy.equals("MedianRankingStrategy")) {
      return new MedianRankingStrategy();
    } else {
      throw new Exception("Error");
    }
  }

  private static void parseCommandCalculateScore(ReviewSystem reviewSystem, String[] inputTokens) {
    try {
      RankingStrategy rankingStrategy = getRankingStrategy(inputTokens[3]);
      reviewSystem.calculateScore(inputTokens[1], inputTokens[2], rankingStrategy);
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }

  private static void parseCommandFindStrength(ReviewSystem reviewSystem, String[] inputTokens) {
    try {
      RankingStrategy rankingStrategy = getRankingStrategy(inputTokens[3]);
      reviewSystem.findStrength(inputTokens[1], inputTokens[2], rankingStrategy);
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }

  private static void parseCommandFindWeakness(ReviewSystem reviewSystem, String[] inputTokens) {
    try {
      RankingStrategy rankingStrategy = getRankingStrategy(inputTokens[3]);
      reviewSystem.findWeakness(inputTokens[1], inputTokens[2], rankingStrategy);
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Error");
      return;
    }

    String inputLine = "";
    String[] inputTokens;
    BufferedReader inputBuffer;

    try {
      inputBuffer = new BufferedReader(new FileReader(args[0]));
      ReviewSystem reviewSystem = new ReviewSystem();

      while ((inputLine = inputBuffer.readLine()) != null) {
        try {
          inputTokens = inputLine.split("\\s+");

          if (inputTokens.length >= 2 && inputTokens[0].equals("student")) {
            parseCommandAddStudent(reviewSystem, inputTokens);
          } else if (inputTokens.length >= 2 && inputTokens[0].equals("schoolStrategy")) {
            parseCommandAddLevels(inputTokens);
          } else if (inputTokens.length == 3 && inputTokens[0].equals("designCriterion")) {
            parseCommandAddRubric(reviewSystem, inputTokens);
          } else if (inputTokens.length >= 3 && inputTokens[0].equals("assignment")) {
            parseCommandAddReview(reviewSystem, inputTokens);
          } else if (inputTokens.length == 2 && inputTokens[0].equals("printRubric")) {
            parseCommandPrintRubric(reviewSystem, inputTokens);
          } else if (inputTokens.length == 2 && inputTokens[0].equals("averageCriterion")) {
            parseCommandAverageCriterion(reviewSystem, inputTokens);
          } else if (inputTokens.length == 4 && inputTokens[0].equals("calculateScore")) {
            parseCommandCalculateScore(reviewSystem, inputTokens);
          } else if (inputTokens.length == 4 && inputTokens[0].equals("findStrength")) {
            parseCommandFindStrength(reviewSystem, inputTokens);
          } else if (inputTokens.length == 4 && inputTokens[0].equals("findWeakness")) {
            parseCommandFindWeakness(reviewSystem, inputTokens);
          } else {
            throw new IOException("No command matched");
          }
        } catch (Exception inputError) {
          System.out.println("Error");
        }
      }

    } catch (Exception bufferError) {
      System.out.println("Error");
    }
  }
}
