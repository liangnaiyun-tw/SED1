import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        String line = "";
        SpreadsheetApplication spreadsheetApplication = new SpreadsheetApplication();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(" ");
                switch (keyValuePair[0]) {
                    case "data":
                        spreadsheetApplication.addData(keyValuePair[1], Integer.parseInt(keyValuePair[2]));
                        break;
                    case "addChart":
                        spreadsheetApplication.addPresentation(keyValuePair[1]);
                        break;
                    case "change":
                        System.out.println(String.format("%s change %s %s.", keyValuePair[1], keyValuePair[2], keyValuePair[3]));
                        spreadsheetApplication.update(keyValuePair[2], Integer.parseInt(keyValuePair[3]));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Input error");
        }
        
    }
    
    
    
}