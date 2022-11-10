import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SmallLibrarySystem {
    private List<BookCopy> bookCopies;
    private List<User> users;
    private List<CheckOut> checkouts;
    public static int bookCopyCount;

    // + System(copies: List<BookCopy>, users: List<User>)
    SmallLibrarySystem(List<BookCopy> copies, List<User> users) {
        this.bookCopies = copies;
        this.users = users;
        this.checkouts = new ArrayList<>();
        SmallLibrarySystem.bookCopyCount = this.bookCopies.size();
    }

    public List<BookCopy> getBookCopies() {
        return this.bookCopies;
    }

    public List<User> getUser() {
        return this.users;
    }

    public List<CheckOut> getCheckOut() {
        return this.checkouts;
    }

    // + checkOut(bookCopyIds: List<Integer>, staffName: String, borrowerName:
    // String): void
    public void checkOut(List<Integer> bookCopyIds, String staffName, String borrowName) throws Exception {
        Staff staff = null;
        Borrower borrower = null;

        if (getUserByName(staffName) == null) {
            throw new Exception("Error");
        } else {
            staff = getUserByName(staffName);
        }

        if (getUserByName(borrower) == null) {
            throw new Exception("Error");
        } else {
            borrower = getUserByName(borrower);
        }

        if (!isStaff(staffName)) {
            throw new Exception("Borrower can not check out the books");
        }

        if (!isBorrower(borrowName)) {
            throw new Exception("Error");
        }

        // check bookCopy exists
        bookCopyIds.forEach(bId -> {
            Optional<BookCopy> bookCopies = this.bookCopies.stream().filter(b -> b.getId() == bId).findAny();
            if (bookCopies.isPresent()) {

            } else {
                try {
                    throw new Exception("Error");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // check Borrower can check out the books count
        if (bookCopyIds.size() > borrower.getMaxCopy()) {
            throw new Exception(
                    "Can not check out since the number of books exceed the limitation of user can check-out");
        }

        // check bookCopy status is AVAILABLEFORCHECKOUT
        bookCopyIds.forEach(bId -> {
            Optional<BookCopy> bookCopies = this.bookCopies.stream().filter(b -> b.getStatus() == Status.CHECKEDOUT)
                    .findAny();
            if (bookCopies.isPresent()) {
                try {
                    throw new Exception("Can not check out since the book is checked out");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        bookCopyIds.forEach(bId -> {
            CheckOut checkOut = new CheckOut();
            checkOut.setBookCopy(getBookCopyById(bId));
            checkOut.setBorrower(borrower);
            checkouts.add(checkOut);
        });

    }

    // + returnBook(bookCopyId: int, staffName: String): void
    public void returnBook(int bookCopyId, String staffName) throws Exception {
        Staff staff = null;

        if (getUserByName(staffName) == null) {
            throw new Exception("Error");
        } else {
            staff = getUserByName(staffName);
        }

        if (!isStaff(staffName)) {
            throw new Exception("Borrower can not return book");
        }

        BookCopy bookCopy = getBookCopyById(bookCopyId);
        if (bookCopy == null) {
            throw new Exception("Error");
        } else {
            if(bookCopy.getStatus().equals(Status.CHECKEDOUT)){
                bookCopy.setStatus(Status.AVAILABLEFORCHECKOUT);
                checkouts.forEach(c -> {
                    if(c.getBookCopy().getId() == bookCopy.getId()){
                        this.checkouts.remove(c);
                    }
                });
            } 
            else{
                throw new Exception("Can not return since the book isn't checked out");
            }
        }
    }

    // + addBook(bookCopy: BookCopy, staffName: String): void
    public void addBook(BookCopy bookCopy, String staffName) throws Exception {

    }

    // + removeBook(bookCopyId: int, staffName: String): void
    public void removeBook(int bookCopyId, String staffName) throws Exception {

    }

    // + queryByAuthor(author: Author): List<BookCopy>
    public List<BookCopy> queryByAuthor(Author author) throws Exception {
        return null;
    }

    // + queryBySubject(subject: Subject): List<BookCopy>
    public List<BookCopy> queryBySubject(Subject subject) throws Exception {
        return null;
    }

    // + findOutCheckBooks(staffName: String, borrowerName: String): List<BookCopy>
    public List<BookCopy> findOutCheckBooks(String staffName, String borrowName) throws Exception {
        return null;
    }

    // + findOutLastBorrower(bookCopyId: int, staffName: String): Borrower
    public Borrower findOutLastBorrower(int bookCopyId, String staffName) throws Exception {
        return null;
    }

    // - isStaff(userName: String): boolean
    private boolean isStaff(String userName) {
        // TODO
        return true;
    }

    // - isBorrower(userName: String): boolean
    private boolean isBorrower(String userName) {
        // TODO
        return true;
    }
};
