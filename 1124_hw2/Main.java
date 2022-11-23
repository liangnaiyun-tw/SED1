import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static BufferedReader inputBuffer;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
            return;
        }

        try {
            String format;
            String content;
            Converter converter;
            Reader reader = new Reader();

            inputBuffer = new BufferedReader(new FileReader(args[0]));
            while ((format = inputBuffer.readLine()) != null) {
                content = inputBuffer.readLine();
                try {
                    if (format.equals("TeX")) {
                        converter = new TexTextDocumentConverter();
                    } else if (format.equals("TextWidget")) {
                        converter = new TextWidgetConverter();
                    } else {
                        throw new IllegalArgumentException("Invalid text format");
                    }
                    System.out.println(reader.convert(converter, new RTFDocument(content)));
                } catch (IllegalArgumentException inputError) {
                    System.out.println("Input Error");
                }
            }
        } catch (IOException bufferError) {
            System.out.println("Input Error");
        }
    }
}