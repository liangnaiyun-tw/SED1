import java.util.List;

public class TexStrategy implements LinebreakingStrategy {
    @Override
    public void arrange(Composition composition) {
        List<Component> components = composition.getComponents();
        for (int i = 0; i < components.size(); ++i) {
            Component component = components.get(i);
            System.out.print(String.format("[%d]%s", component.getNatrualSize(), component.getContent()));
            if (i == components.size()-1 || component.getContent().equals("<ParagraphEnd>")) {
                System.out.print("\n");
            }
            else {
                System.out.print(" ");
            }
        }
    }
}
