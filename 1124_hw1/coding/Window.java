package coding;

public class Window extends Widget {

  public Window(String name) {
    super(name);
    super.setOrder(1);
  }

  public void display() {
    super.getStyle().displayWindow(super.getName());
  }
}