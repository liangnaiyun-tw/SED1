public class TexTextDocument implements TextDocument {
    private String content;

    public TexTextDocument(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
