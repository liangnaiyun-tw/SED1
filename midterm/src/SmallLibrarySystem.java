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
};
