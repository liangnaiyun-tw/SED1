public class ScrollBar extends Widget {

  public ScrollBar(String name) {
    super(name);
    super.setOrder(2);
  }

  public void display() {
    super.getStyle().displayScrollBar(super.getName());
  }
}