package coding;

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
      GUIApplication gui = new GUIApplication();
      inputBuffer = new BufferedReader(new FileReader(args[0]));
      while ((inputLine = inputBuffer.readLine()) != null) {
        try {
          inputTokens = inputLine.split("\\s+");
          if (inputTokens.length == 2) {
            switch (inputTokens[0]) {
              case "Window":
                gui.addWidget(new Window(inputTokens[1]));
                break;
              case "ScrollBar":
                gui.addWidget(new ScrollBar(inputTokens[1]));
                break;
              case "Button":
                gui.addWidget(new Button(inputTokens[1]));
                break;
              default:
                throw new IllegalArgumentException();
            }
          } else if (inputTokens.length == 1 && inputTokens[0].equals("Present")) {
            gui.display();
          } else if (inputTokens.length == 1 && inputTokens[0].equals("PM")) {
            gui.setStyle(new PM());
          } else if (inputTokens.length == 1 && inputTokens[0].equals("Motif")) {
            gui.setStyle(new Motif());
          } else {
            throw new IllegalArgumentException();
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
