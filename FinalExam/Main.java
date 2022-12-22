import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main {

  public static void main(String[] args) {
    String inputLine = "";
    String[] inputTokens;

    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      while ((inputLine = reader.readLine()) != null) {
        try {
        } catch (IOException e) {
          System.out.println("Input error");
        }
      }

    } catch (IOException e) {
      System.out.println("Input error");
    }

  }


}
