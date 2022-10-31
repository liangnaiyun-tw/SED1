import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static BufferedReader inputBuffer;

    static public void main (String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
            return;
        }

        try {
            inputBuffer = new BufferedReader(new FileReader(args[0]));
        }
        catch (Exception bufferError) {
            System.out.println("Input Error");
            try {
                inputBuffer.close();
            }
            catch (IOException ioError) {
                
            }
        }
    }
}