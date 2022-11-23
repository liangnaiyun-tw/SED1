import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

  static BufferedReader inputBuffer;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Input Error");
      return;
    }

    String inputLine;
    ChocolateBoiler chocolateBoiler = ChocolateBoiler.getInstance();
    try {
      inputBuffer = new BufferedReader(new FileReader(args[0]));
      while ((inputLine = inputBuffer.readLine()) != null) {
        try {

          if (inputLine.equals("Fill") || inputLine.equals("Boil") || inputLine.equals("Drain")) {
            try {
              chocolateBoiler.execute(inputLine);
            } catch (Exception inputError) {
            }
          } else {
            throw new IllegalArgumentException("No command matched");
          }
        } catch (Exception inputError) {
          System.out.println("Input Error");
        }
      }
    } catch (Exception bufferError) {
      System.out.println("Input Error");
    }
  }

}
