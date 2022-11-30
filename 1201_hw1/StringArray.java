/**
 * StringArray
 */
public class StringArray {
    private String[] strings = new String[0];

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = new String[strings.length];
        this.strings = strings;
    }

}