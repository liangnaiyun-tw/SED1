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
        Application app = new Application();
        try {
            inputBuffer = new BufferedReader(new FileReader(args[0]));
            while ((inputLine = inputBuffer.readLine()) != null) {
                try {
                    if (inputLine.equals("Draw") || inputLine.equals("Text")) {
                        app.createDocument(inputLine);
                    } else if (inputLine.equals("Present")) {
                        app.present();
                    } else {
                        throw new Exception("No command matched");
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