import java.util.ArrayList;
import java.util.List;

public class BookCopy {
    private Author author;
    private Subject subject;
    private Status status;
    private int id;
    private List<Borrower> borrowHistory;

    public BookCopy(String authorName, String subjectName) {
        this.author = new Author(authorName);
        this.subject = new Subject(subjectName);
        this.status = Status.AVAILABLEFORCHECKOUT;
        this.id = SmallLibrarySystem.bookCopyCount;
        SmallLibrarySystem.bookCopyCount++;
        borrowHistory = new ArrayList<Borrower>();
    }

    public void addBorrowHistory(Borrower borrower){
        borrowHistory.add(borrower);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Borrower> getBorrowHistory() {
        return borrowHistory;
    }
}
