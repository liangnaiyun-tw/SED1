import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private static SmallLibrarySystem parsesInitial(BufferedReader inputFile) throws Exception {
        String inputLine;
        String[] inputTokens;

        // Initial books
        inputLine = inputFile.readLine();
        inputTokens = inputLine.split("\\s+");
        if (inputTokens.length != 1) {
            throw new Exception("Error");
        }
        int numberOfBook = Integer.parseInt(inputTokens[0]);
        List<BookCopy> books = new ArrayList<>();
        for (int i = 0; i < numberOfBook; ++i) {
            try {
                inputLine = inputFile.readLine();
                inputTokens = inputLine.split("\\s+");
                if (inputTokens.length != 2) {
                    throw new Exception("Error");
                }
                books.add(new BookCopy(inputTokens[0], inputTokens[1]));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Initial users
        inputLine = inputFile.readLine();
        inputTokens = inputLine.split("\\s+");
        if (inputTokens.length != 1) {
            throw new Exception("Error");
        }
        int numberOfUsers = Integer.parseInt(inputTokens[0]);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; ++i) {
            try {
                inputLine = inputFile.readLine();
                inputTokens = inputLine.split("\\s+");
                if (inputTokens.length == 3 && inputTokens[0].equals("Borrower")) {
                    int maxCopy = Integer.parseInt(inputTokens[2]);
                    if (maxCopy <= 0) {
                        throw new Exception("Error");
                    }
                    users.add(new Borrower(inputTokens[1], maxCopy));
                }
                else if (inputTokens.length == 2 && inputTokens[0].equals("Staff")) {
                    users.add(new Staff(inputTokens[1]));
                }
                else {
                    throw new Exception("Error");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return new SmallLibrarySystem(books, users);
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Input Error");
            return;
        }

        String inputLine;
        String[] inputTokens;
        BufferedReader inputFile;
        try {
            SmallLibrarySystem librarySystem = parseInitial(inputFile);
            inputFile = new BufferedReader(new FileReader(args[0]));
            while ((inputLine = inputFile.readLine()) != null) {
                try {
                    inputTokens = inputLine.split("\\s+");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}