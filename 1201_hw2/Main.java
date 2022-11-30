import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static BufferedReader inputBuffer;
    static final String INPUT_ERROR = "Input Error";

    private static Component parseGroupComponent(BufferedReader inputBuffer) {
        List<Component> components = new ArrayList<>();
        Component component;
        while ((component = parseCommand(inputBuffer)) != null) {
            components.add(component);
        }
        return new GroupComponent(components);
    }

    private static Component parseCommand(BufferedReader inputBuffer) {
        String inputLine;
        String[] inputTokens;

        try {
            inputLine = inputBuffer.readLine();
            if (inputLine == null) {
                return null;
            }
            inputTokens = inputLine.trim().split("\\s+");
            if (inputTokens.length == 1 && inputTokens[0].equals("<Line/>")) {
                return new Line();
            } else if (inputTokens.length == 1 && inputTokens[0].equals("<Text/>")) {
                return new Text();
            } else if (inputTokens.length == 1 && inputTokens[0].equals("<Rectangle/>")) {
                return new Rectangle();
            } else if (inputTokens.length == 1 && inputTokens[0].equals("<Group/>")) {
                return new GroupComponent();
            } else if (inputTokens.length == 1 && inputTokens[0].equals("<Group>")) {
                return parseGroupComponent(inputBuffer);
            } else if (inputTokens.length == 1 && (inputTokens[0].equals("</Question>")
                    || inputTokens[0].equals("</Group>"))) {
                return null;
            } else {
                throw new IOException(INPUT_ERROR);
            }
        } catch (IOException inputError) {
            System.out.println(INPUT_ERROR);
            return parseCommand(inputBuffer);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(INPUT_ERROR);
            return;
        }

        Application application = new Application();
        String inputLine;
        Component component;

        try {
            inputBuffer = new BufferedReader(new FileReader(args[0]));
            if ((inputLine = inputBuffer.readLine()) == null
                    || !inputLine.equals("<?xml version=\"1.0\"?>")) {
                throw new IOException("No title");
            }
            if ((inputLine = inputBuffer.readLine()) == null || !inputLine.equals("<Question>")) {
                throw new IOException("No question");
            }
        } catch (IOException inputError) {
            System.out.println(INPUT_ERROR);
            return;
        }

        while ((component = parseCommand(inputBuffer)) != null) {
            application.draw(component);
        }

        /* Close file buffer reader */
        try {
            inputBuffer.close();
        } catch (IOException inputError) {
            System.out.println(INPUT_ERROR);
        }
    }
}
