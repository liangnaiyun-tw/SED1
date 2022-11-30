import java.util.ArrayList;
import java.util.List;

public class Composition {
    private List<Component> components;
    
    public Composition() {
        components = new ArrayList<>();
    }

    public void compose(LinebreakingStrategy strategy) {
        arrange(strategy);
    }

    public void arrange(LinebreakingStrategy strategy) {
        strategy.arrange(this);
    }

    public void addComponent(Component newComponent) {
        components.add(newComponent);
    }

    public List<Component> getComponents() {
        return components;
    }
}
