import java.util.Arrays;
import java.util.List;

public class Application {
    public void draw(Component component) {
        component.draw();
        System.out.println();
    }

    public Component group(List<Component> components) {
        return new GroupComponent(components);
    }
}
