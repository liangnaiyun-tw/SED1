import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Input Error");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            FileViewer viewer = new FileViewer();
            String line;
            String[] inputTokens;

            while((line = reader.readLine()) != null){
                try{
                    inputTokens = line.split("\\s+");
                    if(inputTokens.length == 2 && inputTokens[1].equals("display")){
                        parseCommandDisplay(viewer, inputTokens[0]);
                    }
                    else if(inputTokens.length == 2){
                        parseCommandNew(viewer, inputTokens[0], inputTokens[1]);
                    }
                    else if(inputTokens.length >= 3 && inputTokens[1].equals("add")){
                        parseCommandAdd(viewer, inputTokens[0], Arrays.copyOfRange(inputTokens, 2, inputTokens.length));
                    }
                    else{
                        throw new Exception("Invalid command format!");
                    }
                } catch (Exception ex){
                    System.out.println("Input Error");
                }
            }

        } catch (IOException e) {
            System.out.println("Input Error");
        }

    }

    private static void parseCommandAdd(FileViewer viewer, String name, String[] elementNames) throws Exception {
        for(String elementName: elementNames){
            viewer.addElementToView(name, elementName);
        }
    }

    private static void parseCommandNew(FileViewer viewer, String name, String text) throws Exception {
        viewer.attach(new TextView(name, text));
    }

    private static void parseCommandDisplay(FileViewer viewer, String viewName) throws Exception {
        viewer.displayView(viewName);
	   System.out.println("steven ha");
    }
}
