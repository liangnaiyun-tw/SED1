import java.util.ArrayList;
import java.util.List;

abstract class View {
    private String name;
    List<Element> elements = new ArrayList<>();

    public View(String name) {
        this.name = name;
    }

    abstract void display();

    public String getName() {
        return name;
    }

    public void attach(Element element){
        elements.add(element);
    }
    public void detach(Element element){
        elements.remove(element);
    }
}
