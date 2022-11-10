import java.util.ArrayList;
import java.util.List;

public class System {
    private List<BookCopy> bookCopies;
    private List<User> users;
    private List<CheckOut> checkouts;
    public static int bookCopyCount;

    // + System(copies: List<BookCopy>, users: List<User>)
    System(List<BookCopy> copies, List<User> users) {
        this.bookCopies = copies;
        this.users = users;
        this.checkouts = new ArrayList<>();
        System.bookCopyCount = this.bookCopies.size();
    }

    // + checkOut(bookCopyIds: List<Integer>, staffName: String, borrowerName:
    // String): void
    public void checkOut(List<Integer> bookCopyIds, String staffName, String borrowName) throws Exception {

    }

    // + returnBook(bookCopyId: int, staffName: String): void
    public void returnBook(int bookCopyId, String staffName) throws Exception {

    }

    // + addBook(bookCopy: BookCopy, staffName: String): void
    public void addBook(BookCopy bookCopy, String staffName) throws Exception {

    }

    // + removeBook(bookCopyId: int, staffName: String): void
    public void removeBook(int bookCopyId, String staffName) {

    }

    // + queryByAuthor(author: Author): List<BookCopy>
    public List<BookCopy> queryByAuthor(Author author) {

    }

    // + queryBySubject(subject: Subject): List<BookCopy>
    public List<BookCopy> queryBySubject(Subject subject) {

    }

    // + findOutCheckBooks(staffName: String, borrowerName: String): List<BookCopy>
    public List<BookCopy> findOutCheckBooks(String staffName, String borrowName) {

    }

    // + findOutLastBorrower(bookCopyId: int, staffName: String): Borrower
    public Borrower findOutLastBorrower(int bookCopyId, String staffName) {

    }

    // - isStaff(userName: String): boolean
    private boolean isStaff(String userName) {

    }

    // - isBorrower(userName: String): boolean
    private boolean isBorrower(String userName) {

    }
};
