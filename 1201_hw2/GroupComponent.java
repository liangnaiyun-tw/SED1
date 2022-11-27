import java.util.ArrayList;
import java.util.List;

public class GroupComponent implements Component {
    private List<Component> components;

    public GroupComponent() {
        components = new ArrayList<>();
    }

    public GroupComponent(List<Component> components) {
        this.components = components;
    }

    @Override
    public void draw() {
        System.out.print("Group:{");
        for (Component component : components) {
            component.draw();
            System.out.print(" ");
        }
        System.out.print("}");
    }
}
