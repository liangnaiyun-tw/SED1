public class ArrayStrategy implements LinebreakingStrategy {
    @Override
    public void arrange(Composition composition) {
        int count = 0;
        for (Component component: composition.getComponents()) {
            System.out.print(String.format("[%d]%s", component.getNatrualSize(), component.getContent()));
            ++count;
            if (count == 3) {
                System.out.print("\n");
                count = 0;
            }
            else {
                System.out.print(" ");
            }
        }
    }
}
