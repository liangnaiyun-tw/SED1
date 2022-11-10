import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private static SmallLibrarySystem parseInitial(BufferedReader inputFile) throws Exception {
        String inputLine;
        String[] inputTokens;

        // Initial books
        inputLine = inputFile.readLine();
        inputTokens = inputLine.split("\\s+");
        if (inputTokens.length != 1) {
            throw new Exception("Error");
        }
        
        int numberOfBook = Integer.parseInt(inputTokens[0]);
        if (numberOfBook < 0) {
            throw new Exception("Error");
        }
        
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
        if (numberOfUsers < 0) {
            throw new Exception("Error");
        }

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
            catch (NumberFormatException e) {
                System.out.println("Error");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return new SmallLibrarySystem(books, users);
    }

    private static void parseCommandAddBook(SmallLibrarySystem system, String staffName, BufferedReader inputFile) throws Exception {
        String author;
        String subject;
        
        String inputLine;
        String[] inputTokens;
        try {
            if ((inputLine = inputFile.readLine()) != null) {
                inputTokens = inputLine.split("\\s+");
                if (inputTokens.length != 2) {
                    throw new Exception("Error");
                }
                author = inputTokens[0];
                subject = inputTokens[1];
            }
            else {
                throw new Exception("Error");
            }
        }
        catch (Exception e) {
            throw e;
        }

        try {
            system.addBook(new BookCopy(author, subject), staffName);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandRemoveBook(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        int bookId = Integer.parseInt(inputTokens[2]);
        String userName = inputTokens[0];
        
        try {
            system.removeBook(bookId, userName);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandCheckOut(SmallLibrarySystem system, String staffName, String borrower, BufferedReader inputFile) throws Exception {
        List<Integer> bookIds = new ArrayList<>();

        String inputLine;
        String[] inputTokens;
        try {
            if ((inputLine = inputFile.readLine()) != null) {
                inputTokens = inputLine.split("\\s+");
                for (int i = 0; i < inputTokens.length; ++i) {
                    int id = Integer.parseInt(inputTokens[i]);
                    if (id < 0) {
                        throw new Exception("Error");
                    }
                    bookIds.add(Integer.parseInt(inputTokens[i]));
                }
            }
            else {
                throw new Exception("Error");
            }
        }
        catch (Exception e) {
            throw e;
        }

        try {
            system.checkOut(bookIds, staffName, borrower);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandReturn(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        String staffName = inputTokens[0];
        int bookId = Integer.parseInt(inputTokens[2]);
        if (bookId < 0) {
            throw new Exception("Error");
        }

        try {
            system.removeBook(bookId, staffName);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandListAuthor(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        String author = inputTokens[2];
        
        try {
            system.queryByAuthor(author);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandListSubject(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        String subject = inputTokens[2];

        try {
            system.queryBySubject(subject);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandFindChecked(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        String staff = inputTokens[0];
        String borrower = inputTokens[2];

        try {
            system.findOutCheckBooks(staff, borrower);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseCommandBorrower(SmallLibrarySystem system, String[] inputTokens) throws Exception {
        String staff = inputTokens[0];
        int bookId = Integer.parseInt(inputTokens[2]);
        if (bookId < 0) {
            throw new Exception("Error");
        }

        try {
            system.findOutLastBorrower(bookId, staff);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
            inputFile = new BufferedReader(new FileReader(args[0]));
            SmallLibrarySystem librarySystem = parseInitial(inputFile);
            while ((inputLine = inputFile.readLine()) != null) {
                try {
                    inputTokens = inputLine.split("\\s+");
                    if (inputTokens.length == 2 && inputTokens[1].equals("addBook")) {
                        parseCommandAddBook(librarySystem, inputTokens[0], inputFile);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("removeBook")) {
                        parseCommandRemoveBook(librarySystem, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("checkout")) {
                        parseCommandCheckOut(librarySystem, inputTokens[0], inputTokens[2], inputFile);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("return")) {
                        parseCommandReturn(librarySystem, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("listAuthor")) {
                        parseCommandListAuthor(librarySystem, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("listSubject")) {
                        parseCommandListSubject(librarySystem, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("findChecked")) {
                        parseCommandFindChecked(librarySystem, inputTokens);
                    }
                    else if (inputTokens.length == 3 && inputTokens[1].equals("Borrower")) {
                        parseCommandBorrower(librarySystem, inputTokens);
                    }
                    else {
                        throw new Exception("Error");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}