
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  static BufferedReader inputBuffer;

  public static void drawCloseBox(Window window) throws Exception {
    if (window instanceof TransientWindow) {
      ((TransientWindow) window).drawCloseBox();
    } else {
      throw new Exception();
    }
  }

  public static void drawBorder(Window window) throws Exception {
    if (window instanceof IconWindow) {
      ((IconWindow) window).drawBorder();
    } else {
      throw new Exception();
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Input Error");
      return;
    }
    try {
      String inputLine;
      String inputLine2;
      String[] inputTokens;
      String[] inputTokens2;
      inputBuffer = new BufferedReader(new FileReader(args[0]));
      while ((inputLine = inputBuffer.readLine()) != null
          && (inputLine2 = inputBuffer.readLine()) != null) {
        try {
          inputTokens = inputLine.split("\\s+");
          inputTokens2 = inputLine2.split("\\s+");
          if (inputTokens.length == 3 && inputTokens2.length == 1) {
            if (inputTokens[0].equals("window") && inputTokens[1].equals("IconWindow")) {
              Window window = new IconWindow();
              switch (inputTokens[2]) {
                case "XWindow":
                  window.setWindowImplementor(new XWindow());
                  if (inputTokens2[0].equals("drawBorder")) {
                    drawBorder(window);
                  } else {
                    throw new IllegalArgumentException();
                  }
                  break;
                case "PMWindow":
                  window.setWindowImplementor(new PMWindow());
                  if (inputTokens2[0].equals("drawBorder")) {
                    drawBorder(window);
                  } else {
                    throw new IllegalArgumentException();
                  }
                  break;
                default:
                  throw new IllegalArgumentException();
              }
            } else if (inputTokens[0].equals("window") && inputTokens[1].equals(
                "TransientWindow")) {
              Window window = new TransientWindow();
              switch (inputTokens[2]) {
                case "XWindow":
                  window.setWindowImplementor(new XWindow());
                  if (inputTokens2[0].equals("drawCloseBox")) {
                    drawCloseBox(window);
                  } else {
                    throw new IllegalArgumentException();
                  }
                  break;
                case "PMWindow":
                  window.setWindowImplementor(new PMWindow());
                  if (inputTokens2[0].equals("drawCloseBox")) {
                    drawCloseBox(window);
                  } else {
                    throw new IllegalArgumentException();
                  }
                  break;
                default:
                  throw new IllegalArgumentException();
              }
            } else {
              throw new IllegalArgumentException();
            }
          } else {
            throw new IllegalArgumentException();
          }
        } catch (Exception inputError) {
          System.out.println("Input Error");
        }
      }
    } catch (IOException bufferError) {
      System.out.println("Input Error");
    }
  }
}
