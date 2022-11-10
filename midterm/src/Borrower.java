public class Borrower extends User{
    public Borrower(String name) {
        super(name);
        this.setName(name);
    }

    public Borrower(String name, int maxCopy) {
        super(name);
        this.setName(name);
        this.setMaxCopy(maxCopy);
    }

    private int maxCopy;

    public int getMaxCopy() {
        return maxCopy;
    }

    public void setMaxCopy(int maxCopy) {
        this.maxCopy = maxCopy;
    }
}
