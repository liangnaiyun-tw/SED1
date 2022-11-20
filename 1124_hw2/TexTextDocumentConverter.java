public class TexTextDocumentConverter implements Converter {
    @Override
    public TextDocument convert(RTFDocument rtfDocument) throws IllegalArgumentException {
        String result = "";
        for (RTFToken token : rtfDocument.getTokens()) {
            if (token instanceof Character) {
                result += "c";
            } else if (token instanceof FontChange) {
                result += "_";
            } else if (token instanceof Paragraph) {
                result += "|";
            } else {
                throw new IllegalArgumentException("Invalid token type.");
            }
        }
        return new TexTextDocument(result);
    }
}
