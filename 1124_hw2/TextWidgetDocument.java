public class TextWidgetDocument implements TextDocument {
    private String content;

    public TextWidgetDocument(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
