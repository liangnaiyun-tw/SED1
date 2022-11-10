public class CheckOut {
    private BookCopy bookCopy;
    public BookCopy getBookCopy() {
        return bookCopy;
    }
    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
    private Borrower borrower;
    public Borrower getBorrower() {
        return borrower;
    }
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}
