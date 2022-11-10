import java.util.ArrayList;
import java.util.List;

public class SmallLibrarySystem {
    private List<BookCopy> bookCopies;
    private List<User> users;
    private List<CheckOut> checkouts;
    public static int bookCopyCount;

    public void printState() {
        System.out.println("Book");
        for (BookCopy book : bookCopies) {
            System.out.println(
                    String.format("%s %s %d", book.getAuthor().getName(), book.getSubject().getName(), book.getId()));
        }

        System.out.println("User");
        for (User user : users) {
            System.out.println(
                    String.format("%s %s", (this.isStaff(user.getName()) ? "Staff" : "Borrower"), user.getName()));
        }

        System.out.println("Checkout");
        for (CheckOut checkout : this.checkouts) {
            System.out.println(
                    String.format("%s %s", checkout.getBookCopy().getId(), checkout.getBorrower().getName()));
        }

        System.out.println();
    }

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
        User staff = getUserByName(staffName);
        Borrower borrower = null;

        // check if the staff exists
        if (staff == null) {
            throw new Exception("Error");
        }

        // check if the user is a staff
        if (!isStaff(staffName)) {
            throw new Exception("Borrower can not check out the books");
        }

        // check if the borrower exists
        if (getUserByName(borrowName) == null) {
            throw new Exception("Error");
        } else {
            User user = getUserByName(borrowName);
            borrower = ((Borrower) user);
        }

        // check if the user is a borrower
        if (!isBorrower(borrowName)) {
            throw new Exception("Error");
        }

        // check if bookCopsyIds is null
        if (bookCopyIds == null) {
            return;
        }

        // check bookCopy exists
        for (Integer bookId : bookCopyIds) {
            BookCopy bookCopy = this.getBookCopyById(bookId);
            if (bookCopy == null) {
                throw new Exception("Error");
            }
        }

        // check Borrower can check out the books count
        if (bookCopyIds.size() > borrower.getMaxCopy()) {
            throw new Exception(
                    "Can not check out since the number of books exceed the limitation of user can check-out");
        }

        // check bookCopy status is AVAILABLEFORCHECKOUT
        for (Integer bookId : bookCopyIds) {
            BookCopy bookCopy = getBookCopyById(bookId);
            if (bookCopy.getStatus() != Status.AVAILABLEFORCHECKOUT) {
                throw new Exception("Can not check out since the book is checked out");
            }
        }

        bookCopyIds.forEach(bId -> {
            User user = getUserByName(borrowName);
            Borrower b = (Borrower) user;
            BookCopy bookCopy = getBookCopyById(bId);

            CheckOut checkOut = new CheckOut();
            checkOut.setBookCopy(bookCopy);
            checkOut.setBorrower(b);
            checkouts.add(checkOut);

            bookCopy.addBorrowHistory(b);
            bookCopy.setStatus(Status.CHECKEDOUT);
        });

    }

    // + returnBook(bookCopyId: int, staffName: String): void
    public void returnBook(int bookCopyId, String staffName) throws Exception {
        User staff = getUserByName(staffName);

        // check if the user exists
        if (staff == null) {
            throw new Exception("Error");
        }

        // check if the user is a staff
        if (!isStaff(staffName)) {
            throw new Exception("Borrower can not return book");
        }

        BookCopy bookCopy = getBookCopyById(bookCopyId);
        // check if the book exists
        if (bookCopy == null) {
            throw new Exception("Error");
        }

        // check book status
        if (bookCopy.getStatus().equals(Status.AVAILABLEFORCHECKOUT)) {
            throw new Exception("Can not return since the book isn't checked out");
        }

        // checkout
        bookCopy.setStatus(Status.AVAILABLEFORCHECKOUT);
        for (CheckOut checkOut : this.checkouts) {
            if (checkOut.getBookCopy().getId() == bookCopyId) {
                this.checkouts.remove(checkOut);
                break;
            }
        }
    }

    // + addBook(bookCopy: BookCopy, staffName: String): void
    public void addBook(BookCopy bookCopy, String staffName) throws Exception {
        User staff = this.getUserByName(staffName);
        // check if the user exists
        if (staff == null) {
            throw new Exception("Error");
        }

        // check if the user is a staff
        if (!isStaff(staffName)) {
            throw new Exception("Borrower can not add book");
        }

        // add book
        this.bookCopies.add(bookCopy);
        SmallLibrarySystem.bookCopyCount++;
    }

    // + removeBook(bookCopyId: int, staffName: String): void
    public void removeBook(int bookCopyId, String staffName) throws Exception {
        BookCopy bookToRemove = this.getBookCopyById(bookCopyId);
        User staff = this.getUserByName(staffName);

        // check if the user exists
        if (staff == null) {
            throw new Exception("Error");
        }

        // check if the user is a staff
        if (!this.isStaff(staffName)) {
            throw new Exception("Borrower can not remove book");
        }

        // check if the book exists
        if (bookToRemove == null) {
            throw new Exception("Error");
        }

        // check if the book is checked out
        if (bookToRemove.getStatus() == Status.CHECKEDOUT) {
            throw new Exception("Error");
        }

        // remove the book
        this.bookCopies.remove(bookToRemove);
    }

    // + queryByAuthor(author: Author): List<BookCopy>
    public List<BookCopy> queryByAuthor(String userName, String authorName) throws Exception {
        User user = this.getUserByName(userName);
        if (user == null) {
            throw new Exception("Error");
        }

        List<BookCopy> result = new ArrayList<>();
        for (BookCopy bookCopy : this.bookCopies) {
            if (bookCopy.getAuthor().getName().equals(authorName)) {
                result.add(bookCopy);
                System.out.println(String.format("ID: %d Author: %s Subject: %s", bookCopy.getId(),
                        bookCopy.getAuthor().getName(), bookCopy.getSubject().getName()));
            }
        }
        return result;
    }

    // + queryBySubject(subject: Subject): List<BookCopy>
    public List<BookCopy> queryBySubject(String userName, String subjectName) throws Exception {
        User user = this.getUserByName(userName);
        if (user == null) {
            throw new Exception("Error");
        }

        List<BookCopy> result = new ArrayList<>();
        for (BookCopy bookCopy : this.bookCopies) {
            if (bookCopy.getSubject().getName().equals(subjectName)) {
                result.add(bookCopy);
                System.out.println(String.format("ID: %d Author: %s Subject: %s", bookCopy.getId(),
                        bookCopy.getAuthor().getName(), bookCopy.getSubject().getName()));
            }
        }
        return result;
    }

    // + findOutCheckBooks(staffName: String, borrowerName: String): List<BookCopy>
    public List<BookCopy> findOutCheckBooks(String staffName, String borrowName) throws Exception {

        List<BookCopy> borrowedBooks = new ArrayList<>();

        if (getUserByName(staffName) == null) {
            throw new Exception("Error");
        }
        if (getUserByName(borrowName) == null) {
            throw new Exception("Error");
        }

        if (isStaff(borrowName)) {
            throw new Exception("Error");
        }

        if (isBorrower(staffName)) {
            if (staffName.equals(borrowName)) {
                for (CheckOut checkout : checkouts) {
                    if (checkout.getBorrower().getName().equals(borrowName)) {
                        borrowedBooks.add(checkout.getBookCopy());
                    }
                }
            } else {
                throw new Exception("Borrower can not find books checked out by other users");
            }
        } else if (isStaff(staffName)) {
            for (CheckOut checkout : checkouts) {
                if (checkout.getBorrower().getName().equals(borrowName)) {
                    borrowedBooks.add(checkout.getBookCopy());
                }
            }
        } else {
            throw new Exception("Error");
        }

        for (BookCopy copy : borrowedBooks) {
            System.out.println(
                    String.format("ID: %d Author: %s Subject: %s", copy.getId(), copy.getAuthor().getName(),
                            copy.getSubject().getName()));
        }
        return borrowedBooks;
    }

    // + findOutLastBorrower(bookCopyId: int, staffName: String): Borrower
    public Borrower findOutLastBorrower(int bookCopyId, String staffName) throws Exception {
        BookCopy copy = getBookCopyById(bookCopyId);
        if (getUserByName(staffName) == null) {
            throw new Exception("Error");
        }
        if (isStaff(staffName)) {
            if (copy == null) {
                throw new Exception("Error");
            }
            if (copy.getBorrowHistory().size() == 0) {
                System.out.println();
                return null;
            } else {
                Borrower borrower = copy.getBorrowHistory().get(copy.getBorrowHistory().size() - 1);
                System.out.println(String.format("User: %s", borrower.getName()));
                return borrower;
            }

        } else if (isBorrower(staffName)) {
            throw new Exception("Borrower can not find borrower");
        } else {
            throw new Exception("Error");
        }
    }

    // - isStaff(userName: String): boolean
    private boolean isStaff(String userName) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getClass().getSimpleName().equals("Staff")) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    // - isBorrower(userName: String): boolean
    private boolean isBorrower(String userName) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getClass().getSimpleName().equals("Borrower")) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    // - getUserByName(userName: String): User
    private User getUserByName(String userName) {
        for (User user : this.users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    // - getBookCopyById(int bookCopyId): BookCopy
    private BookCopy getBookCopyById(int bookCopyId) {
        for (BookCopy bookCopy : this.bookCopies) {
            if (bookCopy.getId() == bookCopyId) {
                return bookCopy;
            }
        }
        return null;
    }
}