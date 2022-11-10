import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
            return;
        }

        String inputLine;
        String[] inputTokens;
        BufferedReader inputFile;
        try {
            inputFile = new BufferedReader(new FileReader(args[0]));
            while ((inputLine = inputFile.readLine()) != null) {
                inputTokens = inputLine.split("\\s+");
            }
        }
        catch (Exception e) {
            System.out.println("Input Error");
        }
    }
}