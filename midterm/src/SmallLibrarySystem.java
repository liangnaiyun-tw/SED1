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
        User staff = null;
        Borrower borrower = null;

        if (getUserByName(staffName) == null) {
            throw new Exception("Error");
        } else {
            staff = getUserByName(staffName);
        }

        if (getUserByName(borrowName) == null) {
            throw new Exception("Error");
        } else {
            User user = getUserByName(borrowName);
            borrower = ((Borrower)user);
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
            User user = getUserByName(borrowName);
            checkOut.setBorrower((Borrower)user);
            checkouts.add(checkOut);
        });

    }

    // + returnBook(bookCopyId: int, staffName: String): void
    public void returnBook(int bookCopyId, String staffName) throws Exception {
        User staff = null;

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
    public List<BookCopy> queryByAuthor(String authorName) throws Exception {
        List<BookCopy> result = new ArrayList<>();
        for (BookCopy bookCopy : this.bookCopies) {
            if (bookCopy.getAuthor().getName().equals(authorName)) {
                result.add(bookCopy);
                System.out.println(String.format("ID: %d Author: %s Subject: %s", bookCopy.getId(),
                        bookCopy.getAuthor(), bookCopy.getSubject()));
            }
        }
        return result;
    }

    // + queryBySubject(subject: Subject): List<BookCopy>
    public List<BookCopy> queryBySubject(String subjectName) throws Exception {
        List<BookCopy> result = new ArrayList<>();
        for (BookCopy bookCopy : this.bookCopies) {
            if (bookCopy.getSubject().getName().equals(subjectName)) {
                result.add(bookCopy);
                System.out.println(String.format("ID: %d Author: %s Subject: %s", bookCopy.getId(),
                        bookCopy.getAuthor(), bookCopy.getSubject()));
            }
        }
        return result;
    }

    // + findOutCheckBooks(staffName: String, borrowerName: String): List<BookCopy>
    public List<BookCopy> findOutCheckBooks(String staffName, String borrowName) throws Exception {

        List<BookCopy> borrowedBooks = new ArrayList<>();

        if(getUserByName(staffName) == null){
            throw new Exception("Error");
        }
        if(getUserByName(borrowName) == null){
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
        }
        if (isStaff(staffName)) {
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
                    String.format("ID: %d Author: %s Subject: %s", copy.getId(), copy.getAuthor().getName(), copy.getSubject().getName()));
        }
        return borrowedBooks;
    }

    // + findOutLastBorrower(bookCopyId: int, staffName: String): Borrower
    public Borrower findOutLastBorrower(int bookCopyId, String staffName) throws Exception {
        BookCopy copy = getBookCopyById(bookCopyId);
        if(getUserByName(staffName) == null){
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
        for(User user: users){
            if(user.getName().equals(userName)){
                if(user.getClass().getSimpleName().equals("Staff")){
                    return true;
                }
                else{
                    break;
                }
            }
        }
        return false;
    }

    // - isBorrower(userName: String): boolean
    private boolean isBorrower(String userName) {
        for(User user: users){
            if(user.getName().equals(userName)){
                if(user.getClass().getSimpleName().equals("Borrower")){
                    return true;
                }
                else{
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
};
