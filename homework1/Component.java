public class Component {
    private int id;
    private String content;

    private int naturalSize;
    private int stretchability;
    private int shrinkability;

    public Component(int id, String content,
                     int naturalSize, int stretchability, int shrinkability) {
        this.id = id;
        this.content = content;
        this.naturalSize = naturalSize;
        this.stretchability = stretchability;
        this.shrinkability = shrinkability;
    }

    public void changeSize(int newSize) {
        if (newSize < shrinkability || newSize > stretchability) {
            System.out.println(String.format("component %d failed to change size", id));
        }
        else {
            naturalSize = newSize;
            System.out.println(String.format("component %d size changed to %d", id, newSize));
        }
    }

    public int getId() {
        return id;
    }

    public int getNatrualSize() {
        return naturalSize;
    }

    public String getContent() {
        return content;
    }
}
