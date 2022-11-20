public class TextWidgetConverter implements Converter {
    @Override
    public TextDocument convert(RTFDocument rtfDocument) throws IllegalArgumentException {
        String result = "";
        for (RTFToken token : rtfDocument.getTokens()) {
            if (token instanceof Character) {
                result += "<Char>";
            } else if (token instanceof FontChange) {
                result += "<Font>";
            } else if (token instanceof Paragraph) {
                result += "<Paragraph>";
            } else {
                throw new IllegalArgumentException("Invalid token type.");
            }
        }
        return new TextWidgetDocument(result);
    }
}