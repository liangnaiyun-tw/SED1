import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

  static BufferedReader inputBuffer;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Input Error");
      return;
    }
    try {
      String inputLine;
      String[] inputTokens;
      EmailHandler handler = new SpamHandler();
      EmailHandler complaintHandler = new ComplaintHandler();
      EmailHandler fanHandler = new FanHandler();
      handler.setNext(complaintHandler);
      complaintHandler.setNext(fanHandler);
      inputBuffer = new BufferedReader(new FileReader(args[0]));
      while ((inputLine = inputBuffer.readLine()) != null) {
        try {
          inputTokens = inputLine.split("\\s+");
          if (inputTokens.length == 1) {
            switch (inputTokens[0]) {
              case "SPAM":
                handler.handleEmail(new SpamEmail());
                break;
              case "COMPLAINT":
                handler.handleEmail(new ComplaintEmail());
                break;
              case "FAN":
                handler.handleEmail(new FanEmail());
                break;
              default:
                throw new IllegalArgumentException();
            }
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
