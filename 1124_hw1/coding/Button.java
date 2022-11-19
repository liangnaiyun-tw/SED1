package coding;

public class Button extends Widget {

  public Button(String name) {
    super(name);
    super.setOrder(3);
  }

  public void display() {
    super.getStyle().displayButton(super.getName());
  }
}