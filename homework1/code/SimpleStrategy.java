public class SimpleStrategy implements LinebreakingStrategy {
    @Override
    public void arrange(Composition composition) {
        for (Component component: composition.getComponents()) {
            System.out.println(String.format("[%d]%s", component.getNatrualSize(), component.getContent()));
        }
    }
}
