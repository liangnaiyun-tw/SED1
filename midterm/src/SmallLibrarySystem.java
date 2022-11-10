import java.util.ArrayList;
import java.util.List;

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

    }

    // + returnBook(bookCopyId: int, staffName: String): void
    public void returnBook(int bookCopyId, String staffName) throws Exception {

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
            if (bookCopy.getAuthor().getName() == authorName) {
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
            if (bookCopy.getSubject().getName() == subjectName) {
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

        if(isBorrower(staffName)){
            if(staffName.equals(borrowName)){
                for(CheckOut checkout: checkouts){
                    if(checkout.getBorrower().getName().equals(borrowName)){
                        borrowedBooks.add(checkout.getBookCopy());
                    }
                }
            }
            else{
                throw new Exception("Borrower can not find books checked out by other users.");
            }
        }
        if(isStaff(staffName)){
            for(CheckOut checkout: checkouts){
                if(checkout.getBorrower().getName().equals(borrowName)){
                    borrowedBooks.add(checkout.getBookCopy());
                }
            }
        }
        else{
            throw new Exception("The User doesn't exist.");
        }

        for(BookCopy copy: borrowedBooks){
            System.out.println(String.format("ID: %d Author: %s Subject: %s",copy.getId(),copy.getAuthor(),copy.getSubject()));
        }
        return borrowedBooks;
    }

    // + findOutLastBorrower(bookCopyId: int, staffName: String): Borrower
    public Borrower findOutLastBorrower(int bookCopyId, String staffName) throws Exception {
        BookCopy copy = getBookCopyById(bookCopyId);
        if(isStaff(staffName)){
            if(copy == null){
                throw new Exception("the bookCopyId is not found.");
            }
            if(copy.getBorrowHistory().size() == 0){
                System.out.println();
                return null;
            }
            else{
                Borrower borrower = copy.getBorrowHistory().get(copy.getBorrowHistory().size()-1);
                System.out.println(String.format("User: %s",borrower.getName()));
                return borrower;
            }
            
        }
        else if(isBorrower(staffName)){
            throw new Exception("Borrower can not find borrower.");
        }
        else{
            throw new Exception("The user name is not found.");
        }
    }

    // - isStaff(userName: String): boolean
    private boolean isStaff(String userName) {
        // TODO
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
        // TODO
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
            if (user.getName() == userName) {
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
