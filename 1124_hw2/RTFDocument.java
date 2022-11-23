import java.util.List;
import java.util.ArrayList;

public class RTFDocument implements TextDocument {
    private List<RTFToken> tokens;

    public RTFDocument(String content) throws IllegalArgumentException {
        this.tokens = new ArrayList<>();
        char[] tokenChars = content.toCharArray();
        for (char tc : tokenChars) {
            if (tc == 'C') {
                this.tokens.add(new Character());
            } else if (tc == 'F') {
                this.tokens.add(new FontChange());
            } else if (tc == 'P') {
                this.tokens.add(new Paragraph());
            } else {
                throw new IllegalArgumentException("Invalid token type");
            }
        }
    }

    public List<RTFToken> getTokens() {
        return this.tokens;
    }
}
