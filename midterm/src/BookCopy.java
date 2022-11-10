public class BookCopy {
    private Author author;
    private Subject subject;
    private Status status;
    private int id;
    private List<Borrower> borrowHistory;

    public BookCopy(String name) {
        this.name = name;
    }
}
