/**
 * SkipNode
 */
public class SkipNode {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SkipNode(String content) {
        this.content = content;
    }

    public String toString() {
        return "SkipNode:" + content;
    }

}