import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static BufferedReader inputBuffer;

    private static void parseCommandAddComponent(Composition composition, String[] inputTokens) throws Exception {
        int id;
        int naturalSize;
        int shrinkability;
        int stretchability;
        String content;

        id = Integer.parseInt(inputTokens[1]);
        naturalSize = Integer.parseInt(inputTokens[2]);
        shrinkability = Integer.parseInt(inputTokens[3]);
        stretchability = Integer.parseInt(inputTokens[4]);
        content = inputTokens[5];
        if (naturalSize <= 0) {
            throw new Exception("The size of a component should be a positive integer.");
        }
        if (shrinkability <= 0) {
            throw new Exception("The shrinkability of a component should be a positive integer.");
        }
        if (stretchability <= 0) {
            throw new Exception("The stretchability of a component should be a positie integer.");
        }
        if (stretchability < shrinkability) {
            throw new Exception("Stretchability should be greater or equal to shrinkability.");
        }
        if (naturalSize < shrinkability || stretchability < naturalSize) {
            throw new Exception("The natural size should be in the range between shrinkability and stretchability.");
        }

        if (inputTokens[0].equals("Text")) {
            composition.addComponent(new TextElement(id, content, naturalSize, stretchability, shrinkability));
        }
        else if (inputTokens[0].equals("GraphicalElement")) {
            composition.addComponent(new GraphicalElement(id, content, naturalSize, stretchability, shrinkability));
        }
        else {
            throw new Exception("No matched element found.");
        }
    }

    private static void parseCommandChangeComponentSize(Composition composition, String[] inputTokens) throws Exception {
        int id;
        int newSize;

        id = Integer.parseInt(inputTokens[1]);
        newSize = Integer.parseInt(inputTokens[2]);
        if (newSize <= 0) {
            throw new Exception("The new size should be a positive integer.");
        }
        
        composition.getComponents()
                    .stream().filter(component -> component.getId() == id)
                    .findFirst().orElseThrow(() -> new Exception("No matched id found."))
                    .changeSize(newSize);
    }

    private static void parseCommandRequireLayout(Composition composition, String[] inputTokens) throws Exception {
        String strategy;

        strategy = inputTokens[1];
        if (strategy.equals("SimpleComposition")) {
            composition.compose(new SimpleStrategy());
        }
        else if (strategy.equals("TexComposition")) {
            composition.compose(new TexStrategy());
        }
        else if (strategy.equals("ArrayComposition")) {
            composition.compose(new ArrayStrategy());
        }
        else {
            throw new Exception("No matched linebreaking strategy.");
        }
    }

    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
            return;
        }

        try {
            Composition composition = new Composition();
            String inputLine;
            String[] inputTokens;

            inputBuffer = new BufferedReader(new FileReader(args[0]));
            while((inputLine = inputBuffer.readLine()) != null) {
                try {
                    inputTokens = inputLine.split("\\s+");
                    if (inputTokens.length == 6 &&
                        (inputTokens[0].equals("Text") || inputTokens[0].equals("GraphicalElement"))) {
                        
                        parseCommandAddComponent(composition, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[0].equals("ChangeSize")) {
                        parseCommandChangeComponentSize(composition, inputTokens);
                    }
                    else if (inputTokens.length == 2 && inputTokens[0].equals("Require")) {
                        parseCommandRequireLayout(composition, inputTokens);
                    }
                    else {
                        throw new Exception("No command matched");
                    }
                }
                catch (Exception inputError) {
                    System.out.println("Input Error");
                }
            }
        }
        catch (Exception bufferError) {
            System.out.println("Input Error");
        }
    }
}